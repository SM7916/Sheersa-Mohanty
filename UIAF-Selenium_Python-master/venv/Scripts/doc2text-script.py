#!C:\Users\LIJAMANNATHARA\PycharmProjects\UIAF_UI\venv\Scripts\python.exe
# EASY-INSTALL-ENTRY-SCRIPT: 'doc2text==0.2.4','console_scripts','doc2text'
__requires__ = 'doc2text==0.2.4'
import re
import sys
from pkg_resources import load_entry_point

if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(
        load_entry_point('doc2text==0.2.4', 'console_scripts', 'doc2text')()
    )
