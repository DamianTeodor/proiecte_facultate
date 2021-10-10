library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;
entity EX is
     Port (RD1 : in STD_LOGIC_VECTOR (15 downto 0);
           RegDst : in STD_LOGIC;
           rd : in STD_LOGIC_VECTOR(2 downto 0);
           rt : in STD_LOGIC_VECTOR(2 downto 0);
           ALUSrc : in STD_LOGIC;
           RD2 : in STD_LOGIC_VECTOR (15 downto 0);
           Ext_imm : in STD_LOGIC_VECTOR (15 downto 0);
           sa : in STD_LOGIC;
           func : in STD_LOGIC_VECTOR (2 downto 0);
           ALUOp : in STD_LOGIC_VECTOR (1 downto 0);
           PC_plus_1 : in STD_LOGIC_VECTOR (15 downto 0);
           Zero : out STD_LOGIC;
           ALURes : out STD_LOGIC_VECTOR (15 downto 0);
           rWA : out STD_LOGIC_VECTOR(2 downto 0);
           BranchAddress : out STD_LOGIC_VECTOR (15 downto 0));
end EX;

architecture Behavioral of EX is

signal ALUCtrl : std_logic_vector(3 downto 0);
signal A : std_logic_vector(15 downto 0);
signal B : std_logic_vector(15 downto 0);
signal C : std_logic_vector(15 downto 0);

begin

A <= RD1;
B <= RD2 when (ALUSrc = '0') else Ext_Imm;

ALUControl: process(ALUOp, func)
             begin
                case ALUOp is
                    when "00" =>                                 --TIP R
                        case func is 
                            when "000" => ALUCtrl <= "0000";     --ADD
                            when "001" => ALUCtrl <= "0001";     --SUB
                            when "010" => ALUCtrl <= "0010";     --SLL   
                            when "011" => ALUCtrl <= "0011";     --SRL
                            when "100" => ALUCtrl <= "0100";     --AND
                            when "101" => ALUCtrl <= "0101";     --OR
                            when "110" => ALUCtrl <= "0110";     --SLLV
                            when "111" => ALUCtrl <= "0111";     --XOR
                            when others => ALUCtrl <= "0000";
                        end case;
                    when "01" => ALUCtrl <= "0000";              --ADDI, LW, SW, adunare
                    when "10" => ALUCtrl <= "0001";              --BEQ, BNE, scadere
                    when "11" => ALUCtrl <= "1000";              --SLTI,  cod special pentru SLTI
                    when others => ALUCtrl <= "0000";
                end case;
            end process;
            
ALU: process(ALUCtrl, A, B, sa)
     variable inter : std_logic_vector(15 downto 0);
     begin 
        case ALUCtrl is
            when "0000" => C <= A + B;              --ADD
            when "0001" => C <= A - B;              --SUB
            when "0010" =>                          --SLL
                if sa = '1' then
                    C <= '0' & B(15 downto 1);
                end if;
            when "0011" =>                          --SRL
                if sa = '1' then
                    C <= B(14 downto 0) & '0';
                end if;
            when "0100" => C <= A and B;            --AND
            when "0101" => C <= A or B;             --OR
            when "0110" =>                          --SLLV
                case A is
                    when x"0000" => C <= B;
                    when x"0001" => C <= B(14 downto 0) & '0';
                    when x"0002" => C <= B(13 downto 0) & "00";
                    when x"0003" => C <= B(12 downto 0) & "000";
                    when x"0004" => C <= B(11 downto 0) & "0000";
                    when others => C <= x"0000";
                end case;   
            when "0111" => C <= A xor B;             --XOR
            when "1000" =>                           --SLTI
                if signed(A) < signed(B) then
                    C <= x"0001";
                else 
                    C <= x"0000";
                end if;
            when others => C <= x"0000";
        end case;
    end process; 
ALURes <= C;          
Zero <= '1' when conv_integer(C) = 0 else '0';                                  
BranchAddress <= Ext_Imm + PC_plus_1;        
rWA <= rt when (RegDst = '0') else rd;

end Behavioral;
