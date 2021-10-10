library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity MainControl is
    Port ( RegDst : out STD_LOGIC;
           ExtOp : out STD_LOGIC;
           ALUSrc : out STD_LOGIC;
           Branch : out STD_LOGIC;
           Jump : out STD_LOGIC;
           ALUOp : out STD_LOGIC_VECTOR (1 downto 0);
           MemWrite : out STD_LOGIC;
           MemtoReg : out STD_LOGIC;
           RegWrite : out STD_LOGIC;
           Br_ne : out STD_LOGIC;
           Instruction : in STD_LOGIC_VECTOR (2 downto 0));
end MainControl;

architecture Behavioral of MainControl is

begin

Decodificare : process (Instruction)
                begin
                    case Instruction is
                    when "000" => RegDst <= '1'; --R
                                  ExtOp <= '0';
                                  ALUSrc <= '0';
                                  Branch <= '0';
                                  Br_ne <= '0';
                                  Jump <= '0';
                                  MemWrite <= '0';
                                  MemtoReg <= '0';
                                  RegWrite <= '1';
                                  ALUOp <= "00";
                    when "001" => RegDst <= '0'; --ADDI
                                  ExtOp <= '1';
                                  ALUSrc <= '1';
                                  Branch <= '0';
                                  Br_ne <= '0';
                                  Jump <= '0';
                                  MemWrite <= '0';
                                  MemtoReg <= '0';
                                  RegWrite <= '1';
                                  ALUOp <= "01";
                    when "010" => RegDst <= '0'; --LW
                                  ExtOp <= '1';
                                  ALUSrc <= '1';
                                  Branch <= '0';
                                  Br_ne <= '0';
                                  Jump <= '0';
                                  MemWrite <= '0';
                                  MemtoReg <= '1';
                                  RegWrite <= '1';
                                  ALUOp <= "01";  
                    when "011" => RegDst <= '0'; --SW
                                  ExtOp <= '1';
                                  ALUSrc <= '1';
                                  Branch <= '0';
                                  Br_ne <= '0';
                                  Jump <= '0';
                                  MemWrite <= '1';
                                  MemtoReg <= '0';
                                  RegWrite <= '0';
                                  ALUOp <= "01";
                    when "100" => RegDst <= '0'; --BEQ
                                  ExtOp <= '0';
                                  ALUSrc <= '0';
                                  Branch <= '1';
                                  Br_ne <= '0';
                                  Jump <= '0';
                                  MemWrite <= '0';
                                  MemtoReg <= '0';
                                  RegWrite <= '0';
                                  ALUOp <= "10";
                    when "101" => RegDst <= '0'; --SLTI
                                  ExtOp <= '1';
                                  ALUSrc <= '1';
                                  Branch <= '0';
                                  Br_ne <= '0';
                                  Jump <= '0';
                                  MemWrite <= '0';
                                  MemtoReg <= '0';
                                  RegWrite <= '1';
                                  ALUOp <= "11";
                    when "110" => RegDst <= '0'; --BNE
                                  ExtOp <= '0';
                                  ALUSrc <= '0';
                                  Branch <= '0';
                                  Br_ne <= '1';
                                  Jump <= '0';
                                  MemWrite <= '0';
                                  MemtoReg <= '0';
                                  RegWrite <= '0';
                                  ALUOp <= "10";
                    when "111" => RegDst <= '0'; --J
                                  ExtOp <= '0';
                                  ALUSrc <= '0';
                                  Branch <= '0';
                                  Br_ne <= '0';
                                  Jump <= '1';
                                  MemWrite <= '0';
                                  MemtoReg <= '0';
                                  RegWrite <= '0';
                                  ALUOp <= "00";                                            
                    when others => RegDst <= '0';
                                  ExtOp <= '0';
                                  ALUSrc <= '0';
                                  Branch <= '0';
                                  Br_ne <= '0';
                                  Jump <= '0';
                                  MemWrite <= '0';
                                  MemtoReg <= '0';
                                  RegWrite <= '0';
                                  ALUOp <= "00";   
                    end case;
                end process;

end Behavioral;
