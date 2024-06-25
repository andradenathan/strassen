# Diretórios dos arquivos Java
java_src_dir = src/br/ufrj/ic
parser_src_dir = $(java_src_dir)/utils/parsers
matrix_src_dir = $(java_src_dir)/matrix
strassen_src_dir = $(java_src_dir)/strassen
utils_src_dir = $(java_src_dir)/utils

# Comando para compilar Java
javac = javac
main_sources = $(wildcard $(java_src_dir)/*.java)
parser_sources = $(wildcard $(parser_src_dir)/*.java)
matrix_sources = $(wildcard $(matrix_src_dir)/*.java)
strassen_sources = $(wildcard $(strassen_src_dir)/*.java)
utils_sources = $(wildcard $(utils_src_dir)/*.java)
java_sources = $(main_sources) $(parser_sources) $(matrix_sources) $(strassen_sources) $(utils_sources)
java_classes = $(java_sources:.java=.class)

# Comando para executar Java
java = java
generate_class = br.ufrj.ic.utils.parsers.Generate

main_class = br.ufrj.ic.Main

all: $(java_classes)

$(java_classes): $(java_sources)
	$(javac) -d src $(java_sources)

run: all
	cd src && $(java) $(generate_class) $(filter-out $@,$(MAKECMDGOALS)) && cd .. && rm -f $(java_classes)

compile: all
	cd src && $(java) $(main_class) $(filter-out $@,$(MAKECMDGOALS)) && cd .. && rm -f $(java_classes)

test: all
	cd src && $(java) $(main_class) $(filter-out $@,$(MAKECMDGOALS)) --compare && cd .. && rm -f $(java_classes)

%:
	@: