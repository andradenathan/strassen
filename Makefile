java_src_dir = src/br/ufrj/ic/utils/parsers

javac = javac
java_sources = $(wildcard $(java_src_dir)/*.java)
java_classes = $(java_sources:.java=.class)

java = java
generate_class = br.ufrj.ic.utils.parsers.Generate

all: $(java_classes)

$(java_classes): $(java_sources)
	$(javac) -d src $(java_sources)

run: all
	cd src && $(java) $(generate_class) $(filter-out $@,$(MAKECMDGOALS)) && cd .. && rm -f $(java_classes)

%:
	@:
