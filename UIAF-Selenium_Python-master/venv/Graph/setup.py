from cx_Freeze import setup, Executable

base = None    

executables = [Executable("graph.py", base=base)]

packages = ["idna","dash","pandas"]
options = {
    'build_exe': {    
        'packages':packages,
    },    
}

setup(
    name = "<any name>",
    options = options,
    version = "<any number>",
    description = '<any description>',
    executables = executables
)