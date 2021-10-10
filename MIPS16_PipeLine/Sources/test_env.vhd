library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity test_env is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR (4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           led : out STD_LOGIC_VECTOR (15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end test_env;

architecture Behavioral of test_env is
    --Semnale diverse
	signal enable : std_logic;
	signal reset : std_logic;
	signal jump_address_signal : std_logic_vector(15 downto 0);
	signal ExtOp_signal : std_logic;
	signal WD_signal : std_logic_vector(15 downto 0);
	signal Jump_signal : std_logic;
	signal Branch_equal : std_logic;
    signal Branch_not_equal : std_logic;
    signal PCSrc_signal : std_logic;
	signal mux_out : std_logic_vector(15 downto 0);
	
	--Semnale de la IF la REG
	signal instruction_if_to_reg_singnal : std_logic_vector(15 downto 0);
	signal pc_plus_1_if_to_reg_signal : std_logic_vector(15 downto 0);
	
	--Semnale de la ID la REG
	signal RegDst_id_to_reg_signal   : std_logic;
    signal ALUSrc_id_to_reg_signal   : std_logic;
    signal Branch_id_to_reg_signal   : std_logic;
    signal ALUOp_id_to_reg_signal    : std_logic_vector(1 downto 0);
    signal MemWrite_id_to_reg_signal : std_logic;
    signal MemtoReg_id_to_reg_signal : std_logic;
    signal RegWrite_id_to_reg_signal : std_logic;
    signal RD1_id_to_reg_signal     : std_logic_vector(15 downto 0);
    signal RD2_id_to_reg_signal     : std_logic_vector(15 downto 0);
    signal Ext_Imm_id_to_reg_signal : std_logic_vector(15 downto 0);
    signal func_id_to_reg_signal    : std_logic_vector(2 downto 0);
    signal rt_id_to_reg_signal      : std_logic_vector(2 downto 0);
    signal rd_id_to_reg_signal      : std_logic_vector(2 downto 0);
    signal sa_id_to_reg_signal      : std_logic;
    signal Br_ne_id_to_reg_signal : std_logic;
	
	--Semnale de la EX la REG
	signal zero_ex_to_reg_signal          : std_logic;
    signal BranchAddress_ex_to_reg_signal : std_logic_vector(15 downto 0);
    signal ALURes_ex_to_reg_signal        : std_logic_vector(15 downto 0);
    signal rWA_ex_to_reg_signal           : std_logic_vector(2 downto 0);
	
	--Semnale de la MEM la REG
	signal MemData_mem_to_reg_signal : std_logic_vector(15 downto 0);
    signal ALUResOut_mem_to_reg_signal : std_logic_vector(15 downto 0);
    
    --Semnale Registre
    signal REG_IF_ID : std_logic_vector(31 downto 0);
    signal REG_ID_EX : std_logic_vector(82 downto 0);
    signal REG_EX_MEM: std_logic_vector(56 downto 0);
    signal REG_MEM_WB : std_logic_vector(36 downto 0);
    
	component MPG is
	   Port ( en : out STD_LOGIC;
           input : in STD_LOGIC;
           clock : in STD_LOGIC);
	end component;
	component Afisor is
        Port ( clk: in STD_LOGIC;
           digits: in STD_LOGIC_VECTOR(15 downto 0);
           an: out STD_LOGIC_VECTOR(3 downto 0);
           cat: out STD_LOGIC_VECTOR(6 downto 0));
	end component;
    
    component Instruction_Fetch is
    Port ( rst : in STD_LOGIC;
           en : in STD_LOGIC;
           clk : in STD_LOGIC;
           pc_plus_1 : out STD_LOGIC_VECTOR (15 downto 0);
           Instruction : out STD_LOGIC_VECTOR (15 downto 0);
           Jump : in STD_LOGIC;
           Jump_Address : in STD_LOGIC_VECTOR (15 downto 0);
           PCSrc : in STD_LOGIC;
           Branch_Address : in STD_LOGIC_VECTOR (15 downto 0));
    end component;
    
    component ID is
        Port ( RegWrite : in STD_LOGIC;
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
    end component;
    
    component MainControl is
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
    end component;
    
    component EX is
    Port ( RD1 : in STD_LOGIC_VECTOR (15 downto 0);
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
    end component;
    
    component MEM is
    Port ( MemWrite : in STD_LOGIC;
           ALUResin : in STD_LOGIC_VECTOR (15 downto 0);
           RD2 : in STD_LOGIC_VECTOR (15 downto 0);
           CLK : in STD_LOGIC;
           EN : in STD_LOGIC;
           MemData : out STD_LOGIC_VECTOR (15 downto 0);
           ALUResout : out STD_LOGIC_VECTOR (15 downto 0));
    end component;
begin

Generator_Monoimplus_enable: MPG port map (en => enable, 
                                              input => btn(0), 
                                              clock => clk);

Generator_Monoimpuls_reset: MPG port map (en => reset,
                                        input => btn(1),
                                        clock => clk);                                               
                              
Instruction_Fetcher: Instruction_Fetch port map(rst => reset,
                                                en => enable,
                                                clk => clk,
                                                pc_plus_1 => pc_plus_1_if_to_reg_signal,
                                                Instruction => instruction_if_to_reg_singnal,
                                                Jump => Jump_signal,
                                                Jump_Address => jump_address_signal,
                                                PCSrc => PCSrc_signal,
                                                Branch_Address => REG_EX_MEM(50 downto 35)
                                                );                          
                                    
Registru_InstructionFetch_InstructionDecode:
    process(clk)
    begin
        if rising_edge(clk) then
            if enable = '1' then
                REG_IF_ID(31 downto 16) <= instruction_if_to_reg_singnal;
                REG_IF_ID(15 downto 0) <= pc_plus_1_if_to_reg_signal;
            end if;
        end if;
    end process;
    
    jump_address_signal <= REG_IF_ID(15 downto 13) & REG_IF_ID(28 downto 16);
                                    
Controller: MainControl port map(RegDst =>RegDst_id_to_reg_signal ,
                                 ExtOp => ExtOp_signal,
                                 ALUSrc => ALUSrc_id_to_reg_signal,
                                 Branch => Branch_id_to_reg_signal,
                                 Jump => Jump_signal,
                                 ALUOp => ALUOp_id_to_reg_signal,
                                 MemWrite => MemWrite_id_to_reg_signal,
                                 MemtoReg => MemtoReg_id_to_reg_signal,
                                 RegWrite => RegWrite_id_to_reg_signal,
                                 Br_ne => Br_ne_id_to_reg_signal,
                                 Instruction => REG_IF_ID(31 downto 29));

Instruction_decode:
ID port map(RegWrite => REG_MEM_WB(35),
            Instr => REG_IF_ID(31 downto 16),
            WA => REG_MEM_WB(2 downto 0),
            CLK => clk,
            EN => enable,
            ExtOp => ExtOp_signal,
            WD => WD_signal,
            RD1 => RD1_id_to_reg_signal,
            RD2 => RD2_id_to_reg_signal,
            Ext_Imm => Ext_Imm_id_to_reg_signal,
            func => func_id_to_reg_signal,
            rt => rt_id_to_reg_signal,
            rd => rd_id_to_reg_signal,
            sa => sa_id_to_reg_signal);                         

Registru_InstructionDecode_Execution:
    process(clk)
    begin
        if rising_edge(clk) then
            if enable = '1' then
                REG_ID_EX(82) <= RegDst_id_to_reg_signal;
                REG_ID_EX(81) <= ALUSrc_id_to_reg_signal;
                REG_ID_EX(80) <= Br_ne_id_to_reg_signal;
                REG_ID_EX(79) <= Branch_id_to_reg_signal;
                REG_ID_EX(78 downto 77) <= ALUOp_id_to_reg_signal;
                REG_ID_EX(76) <= MemWrite_id_to_reg_signal;
                REG_ID_EX(75) <= MemtoReg_id_to_reg_signal;
                REG_ID_EX(74) <= RegWrite_id_to_reg_signal;
                REG_ID_EX(73 downto 58) <= RD1_id_to_reg_signal;
                REG_ID_EX(57 downto 42) <= RD2_id_to_reg_signal;
                REG_ID_EX(41 downto 26) <= Ext_Imm_id_to_reg_signal;
                REG_ID_EX(25 downto 23) <= func_id_to_reg_signal;
                REG_ID_EX(22) <= sa_id_to_reg_signal;
                REG_ID_EX(21 downto 19) <= rt_id_to_reg_signal;
                REG_ID_EX(18 downto 16) <= rd_id_to_reg_signal;
                REG_ID_EX(15 downto 0) <= REG_IF_ID(15 downto 0);
            end if;
        end if;
    end process;

EXEC: EX port map(RD1           => REG_ID_EX(73 downto 58),
                  RegDst        => REG_ID_EX(82),
                  rd            => REG_ID_EX(18 downto 16),
                  rt            => REG_ID_EX(21 downto 19),
                  ALUSrc        => REG_ID_EX(81),
                  RD2           => REG_ID_EX(57 downto 42),
                  Ext_imm       => REG_ID_EX(41 downto 26),
                  sa            => REG_ID_EX(22),
                  func          => REG_ID_EX(25 downto 23),
                  ALUOp         => REG_ID_EX(78 downto 77),
                  PC_plus_1     => REG_ID_EX(15 downto 0),
                  Zero          => zero_ex_to_reg_signal,
                  ALURes        => ALURes_ex_to_reg_signal,
                  rWA           => rWA_ex_to_reg_signal,
                  BranchAddress => BranchAddress_ex_to_reg_signal);
                  
Registru_Execution_MEM:
    process(clk)
    begin
        if rising_edge(clk) then
            if enable = '1' then
                REG_EX_MEM(56) <= REG_ID_EX(80);
                REG_EX_MEM(55) <= REG_ID_EX(79);
                REG_EX_MEM(54) <= REG_ID_EX(76);
                REG_EX_MEM(53) <= REG_ID_EX(75);
                REG_EX_MEM(52) <= REG_ID_EX(74);
                REG_EX_MEM(51) <= zero_ex_to_reg_signal;
                REG_EX_MEM(50 downto 35) <= BranchAddress_ex_to_reg_signal;
                REG_EX_MEM(34 downto 19) <= ALURes_ex_to_reg_signal;
                REG_EX_MEM(18 downto 16) <= rWA_ex_to_reg_signal;
                REG_EX_MEM(15 downto 0) <= REG_ID_EX(57 downto 42);
            end if;
        end if;
    end process;
   
Logica_de_branch:
    Branch_equal <= REG_EX_MEM(51) and REG_EX_MEM(55);
    Branch_not_equal <= not REG_EX_MEM(51) and REG_EX_MEM(56);
    PCSrc_signal <= Branch_equal or Branch_not_equal;
                  
RAM: MEM port map ( MemWrite => REG_EX_MEM(54),
                    ALUResin => REG_EX_MEM(34 downto 19),
                    RD2 => REG_EX_MEM(15 downto 0),
                    CLK => clk,
                    EN => enable,
                    MemData => MemData_mem_to_reg_signal,
                    ALUResout => ALUResOut_mem_to_reg_signal);

Registru_MEM_WB:
    process(clk)
    begin
        if rising_edge(clk) then
            if enable = '1' then
                REG_MEM_WB(36) <= REG_EX_MEM(53);
                REG_MEM_WB(35) <= REG_EX_MEM(52);
                REG_MEM_WB(34 downto 19) <= ALUResOut_mem_to_reg_signal;
                REG_MEM_WB(18 downto 3) <= MemData_mem_to_reg_signal;
                REG_MEM_WB(2 downto 0) <= REG_EX_MEM(18 downto 16);
            end if;
        end if;
    end process;
    
WD_signal <= REG_MEM_WB(18 downto 3) when (REG_MEM_WB(36) = '1') else REG_MEM_WB(34 downto 19);

Branch_equal <= REG_EX_MEM(51) and REG_EX_MEM(55);
Branch_not_equal <= not REG_EX_MEM(51) and REG_EX_MEM(56);
PCSrc_signal <= Branch_equal or Branch_not_equal;

MUX_MARE:
    process(sw(7 downto 5), instruction_if_to_reg_singnal, pc_plus_1_if_to_reg_signal, REG_ID_EX(73 downto 58), REG_ID_EX(57 downto 42), REG_ID_EX(41 downto 26), ALURes_ex_to_reg_signal, MemData_mem_to_reg_signal, WD_signal)
    begin
        case sw(7 downto 5) is
        when "000" => mux_out <= instruction_if_to_reg_singnal;
        when "001" => mux_out <= pc_plus_1_if_to_reg_signal;
        when "010" => mux_out <= REG_ID_EX(73 downto 58);
        when "011" => mux_out <= REG_ID_EX(57 downto 42);
        when "100" => mux_out <= REG_ID_EX(41 downto 26);
        when "101" => mux_out <= ALURes_ex_to_reg_signal;
        when "110" => mux_out <= MemData_mem_to_reg_signal;
        when "111" => mux_out <= WD_signal;
        when others => mux_out <= x"0000";
        end case;                           
    end process;         
    
LED_uri: led(10 downto 0) <= RegDst_id_to_reg_signal & ExtOp_signal & ALUSrc_id_to_reg_signal & Branch_id_to_reg_signal & Br_ne_id_to_reg_signal & Jump_signal & MemWrite_id_to_reg_signal & MemtoReg_id_to_reg_signal & RegWrite_id_to_reg_signal & ALUOp_id_to_reg_signal;    
                           
af: Afisor port map(clk => clk, 
                    digits => mux_out,
                    an => an,
                    cat => cat);
end Behavioral;
