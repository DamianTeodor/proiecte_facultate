library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
entity Instruction_Fetch is
    Port ( rst : in STD_LOGIC;
           en : in STD_LOGIC;
           clk : in STD_LOGIC;
           pc_plus_1 : out STD_LOGIC_VECTOR (15 downto 0);
           Instruction : out STD_LOGIC_VECTOR (15 downto 0);
           Jump : in STD_LOGIC;
           Jump_Address : in STD_LOGIC_VECTOR (15 downto 0);
           PCSrc : in STD_LOGIC;
           Branch_Address : in STD_LOGIC_VECTOR (15 downto 0));
end Instruction_Fetch;

architecture Behavioral of Instruction_Fetch is
--cod C
--int A[10] = {2,2,2,3,3,3,4,4,4,5};
--int k = 0;

--for(int i = 0; i < 10; i = i + 2){
--	if(A[i] == 3){
--		A[i] = A[i] - 1;
--		k++;
--	}
--}
--Programul ales parcurge din 2 in 2 un vector de 10 elemente si pentru fiecare element care este egal cu 3 il modifica in memorie scazand 1
--si numara cate numere egale cu 3 sunt in vector
type rom_type is array (0 to 31) of std_logic_vector(15 downto 0);
    signal ROM : rom_type := (B"000_000_000_001_0_000", --X"0010", add $1, $0, $0, i = 0, contorul buclei, verificat 
                              B"001_000_100_0001010",   --X"220A", addi $4, $0, 10, se salveaza numarul maxim de iteratii, verificat
                              B"000_000_000_010_0_000", --X"0020", add $2, $0, $0, initializarea indexului locatiei de memorie, verificat
                              B"011_000_000_0000000",   --X"6000", sw $0, 0($0), punem in memorie la locatia 0, verificat
                              B"010_000_101_0000000",   --X"4280", lw $5, 0($0), citim din memorie in registru valoarea contorului, verificat 
                              B"001_000_110_0000011",   --X"2303", addi $6, $0, 3, se pune 3 in registrul 6 pentru a il retine si a putea compara la if, verificat
                              B"100_001_100_0010111",   --X"8617", beq $1, $4, 23, s-a ajuns ca i sa fie 10 sau mai mult? daca da facem salt in afara buclei, verificat
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"010_010_011_0000001",   --X"4981", lw $3, 1($2), in $3 se aduce elementul curent din A, verificat
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"100_011_110_0000111",   --X"8F07", beq $3, $6, 7, este A[i] == 3, daca da facem salt la eticheta, verificat
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"001_010_010_0000010",   --X"2902", addi $2, $2, 2, mergem mai departe in sir, verificat
                              B"001_001_001_0000010",   --X"2482", addi $1, $1, 2, i = i + 2, verificat
                              B"111_0000000000110",     --X"E006", j 6, facem salt inapoi la inceputul buclei, verificat
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"001_011_011_1111111",   --X"2DFF", addi $3, $3, -1, A[i] = A[i] - 1, verificat
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"011_010_011_0000001",   --X"6981", sw $3, 1($2), salvarea noii valori $3 in elementul curent din A, verificat
                              B"001_101_101_0000001",   --X"3681", addi $5, $5, 1, k++, verificat
                              B"001_010_010_0000010",   --X"2902", addi $2, $2, 2, mergem mai departe in sir, verificat
                              B"001_001_001_0000010",   --X"2482", addi $1, $1, 2, i = i + 2, verificat
                              B"111_0000000000110",     --X"E006", j 6, facem salt inapoi la inceputul buclei, verificat
                              B"000_000_000_000_0_000", --X"0000", add $0, $0, $0, NoOp, instructiune ce nu afecteaza 
                              B"011_000_101_0001011",   --X"628B", sw $5, 11($0), salvam k in memorie la adresa k_addr, verificat
                              others => x"0000");
    signal q : std_logic_vector(15 downto 0) := (others => '0');
    signal d : std_logic_vector(15 downto 0) := (others => '0');
    signal b_addr_or_pc1 : std_logic_vector(15 downto 0);
begin

    process(clk)          --PC
    begin
        if rising_edge(clk) then
            if rst = '1' then
               q <= "0000000000000000";
            elsif en = '1' then
               q <= d;  
            end if;
        end if;
    end process;
    Instruction <= ROM(conv_integer(q(4 downto 0)));  -- Instruction
    pc_plus_1 <= q + 1;                               -- PC + 1
    b_addr_or_pc1 <= Branch_Address when (PCSrc = '1') else q + 1;    --multiplexor branch sau pc + 1
    d <= Jump_Address when (Jump = '1') else b_addr_or_pc1;           --multiplexor jump_address sau branch sau pc + 1
                      
end Behavioral;
