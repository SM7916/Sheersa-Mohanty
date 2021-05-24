#!C:\Users\LIJAMANNATHARA\PycharmProjects\UIAF_UI_Selenium\venv\Scripts\python.exe
# EASY-INSTALL-ENTRY-SCRIPT: 'series==2.35.34','console_scripts','serieslibd'
__requires__ = 'series==2.35.34'
import re
import sys
from pkg_resources import load_entry_point

if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(
        load_entry_point('series==2.35.34', 'console_scripts', 'serieslibd')()
    )
