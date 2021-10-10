library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity reg_file is
    Port ( RegWr : in STD_LOGIC;
           RA1 : in STD_LOGIC_VECTOR (2 downto 0);
           RA2 : in STD_LOGIC_VECTOR (2 downto 0);
           WA : in STD_LOGIC_VECTOR (2 downto 0);
           WD : in STD_LOGIC_VECTOR(15 downto 0);
           clk : in STD_LOGIC;
           RD1 : out STD_LOGIC_VECTOR (15 downto 0);
           RD2 : out STD_LOGIC_VECTOR (15 downto 0);
           EN : in STD_LOGIC
           );
end reg_file;

architecture Behavioral of reg_file is

    type reg_array is array (0 to 7) of std_logic_vector(15 downto 0);
    signal reg_arr: reg_array;

begin

    process(clk)
    begin
        if falling_edge(clk) then
            if RegWr = '1' and EN = '1' then
                reg_arr(conv_integer(WA)) <= WD;
            end if;
        end if;
    end process;
    RD1 <= reg_arr(conv_integer(RA1));
    RD2 <= reg_arr(conv_integer(RA2));
    
end Behavioral;
