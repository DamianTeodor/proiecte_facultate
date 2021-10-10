library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
entity ID is
    Port (     RegWrite : in STD_LOGIC;
               Instr : in STD_LOGIC_VECTOR(15 downto 0);
               WA : in STD_LOGIC_VECTOR(2 downto 0);
               CLK : in STD_LOGIC;
               EN : in STD_LOGIC;
               ExtOp: in STD_LOGIC;
               WD: in STD_LOGIC_VECTOR(15 downto 0);
               RD1: out STD_LOGIC_VECTOR(15 downto 0);
               RD2: out STD_LOGIC_VECTOR(15 downto 0);
               Ext_Imm: out STD_LOGIC_VECTOR(15 downto 0);
               func: out STD_LOGIC_VECTOR(2 downto 0);
               rt : out STD_LOGIC_VECTOR(2 downto 0);
               rd : out STD_LOGIC_VECTOR(2 downto 0);
               sa:out STD_LOGIC);
end ID;

architecture Behavioral of ID is

component reg_file is
    Port (     RegWr : in STD_LOGIC;
               RA1 : in STD_LOGIC_VECTOR (2 downto 0);
               RA2 : in STD_LOGIC_VECTOR (2 downto 0);
               WA : in STD_LOGIC_VECTOR (2 downto 0);
               WD : in STD_LOGIC_VECTOR(15 downto 0);
               clk : in STD_LOGIC;
               RD1 : out STD_LOGIC_VECTOR (15 downto 0);
               RD2 : out STD_LOGIC_VECTOR (15 downto 0);
               EN : in STD_LOGIC
               );
end component;

begin

registre: reg_file port map(RegWr => RegWrite,
                            RA1 => Instr(12 downto 10),
                            RA2 =>Instr(9 downto 7),
                            WA => WA,
                            WD => WD,
                            clk => CLK,
                            RD1 => RD1,
                            RD2 => RD2,
                            EN => EN);

Extender: process(ExtOp,Instr)
            begin
            if ExtOp='0' then
               Ext_Imm<="000000000"& Instr(6 downto 0);
               elsif ExtOp='1' and Instr(6)='0' then
                    Ext_Imm<="000000000"& Instr(6 downto 0);  
                    else
                       Ext_Imm<="111111111"& Instr(6 downto 0);          
            end if;            
            end process;
sa <= Instr(3);
func <= Instr(2 downto 0);
rt <= Instr(9 downto 7);
rd <= Instr(6 downto 4);
end Behavioral;