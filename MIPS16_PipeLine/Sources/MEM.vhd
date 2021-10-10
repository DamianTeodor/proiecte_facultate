library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;
entity MEM is
    Port ( MemWrite : in STD_LOGIC;
           ALUResin : in STD_LOGIC_VECTOR (15 downto 0);
           RD2 : in STD_LOGIC_VECTOR (15 downto 0);
           CLK : in STD_LOGIC;
           EN : in STD_LOGIC;
           MemData : out STD_LOGIC_VECTOR (15 downto 0);
           ALUResout : out STD_LOGIC_VECTOR (15 downto 0));
end MEM;

architecture Behavioral of MEM is

type ram_memory is array (0 to 31) of std_logic_vector(15 downto 0);
signal MEM : ram_memory := (x"0000",                       --se initializeaza memoria RAM
                            x"0002",                       -- de la 1 pana la 10 avem array ul
                            x"0002",
                            x"0002",
                            x"0003",
                            x"0003",
                            x"0003",
                            x"0004",
                            x"0004",
                            x"0004",
                            x"0005",
                            others =>x"0000");

begin

process(CLK)
begin
    if rising_edge(CLK) then
      if EN = '1' and MemWrite = '1' then
         MEM(conv_integer(ALUResin(4 downto 0))) <= RD2;
      end if;
    end if;
end process;   
MemData <= MEM(conv_integer(ALUResin(4 downto 0)));
ALUResout <= ALUResin;

end Behavioral;
