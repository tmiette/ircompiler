=========================================================
Project name : IRCompiler
Version : 1.0
Authors : Tom Miette & Sebastien Mouret
---------------------------------------------------------
README.txt
Created January 25, 2008 by Tom Miette & Sebastien Mouret
=========================================================

What is IRCompiler ?
--------------------

IRCompiler is a small compiler for a custom language. This compiler
generates byte code that can be run by the JVM. The custom language
enables to perform simples operations like conditional or loop
instructions. You can also declare functions, manipulate primitive
types and use the standard API classes.

Project directories
-------------------

The project root directory is divided into six main directories.

	1. "bin" which contains the executable jar archive.
	2. "classes" which contains java binaries files (class files) 
	and custom classes used in the custom language.
	3. "docs" which contains the project documentation.
	4. "docs/api" which contains the java documentation (javadoc format).
	5. "ebnf" which contains the ebnf file (grammar specification) to
	generate tatoo sources files.
	6. "gen_src" which contains tatoo sources files.
	7. "lib" which contains java external libraries.
	8. "src" which contains java source files (java files).
	9. "test" which contains some examples of custom language
	code.

Building jar archive
--------------------

The following commands will build the executable jar archive in the
"bin" directory : 
	
	$ ant
	OR
	$ ant jar

Quick start
-----------

To run the program, you can use the java command to launch the jar archive :

	$ java - jar ${user_directory}/bin/IRCompiler.jar <input file> <output file> <print code>
	
	1. <input file> is the file containing the custom code.
	
	2. <output file> is the file in which the code will be generated
	(specify always .class extension).
	
	3. <print code> is an optional option to print (1) or 
	not (0) the custom code.
	
After generating the class file with IRCompiler, you can launch this program
with the simple java command (make sure that your are in the same directory
than the class file) :

	$ java <myfile.class>
	

Others ant tasks
----------------

The build file has others ant tasks 

	1. To compile java source files in the "classes" directory :
	
		$ ant compile
		
	2. To generate java documentation in the "docs/api" directory :
	
		$ ant javadoc

	3. To clean the project directory. This task removes class files, 
	documentation files and the jar archive :
	
		$ ant clean

Libraries
---------

The application used external libraries to run. These libraries are in the
"lib" directory and enable to perform the lexical checkout and to generate
byte code.

	1. tatoo to perform the lexical checkout and to generate classes used 
	during the others checkouts.
	2. asm to generate byte code.

Known bugs
----------

Refer to the documentation file to know the list of what this compiler can do
and cannot do.
