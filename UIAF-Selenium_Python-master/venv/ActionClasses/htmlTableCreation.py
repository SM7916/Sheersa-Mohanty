import  pandas as pd
import numpy as np

def HTML_with_style(df, style=None, random_id=None):
    #from IPython.display import HTML
    import numpy as np
    import re
    df=df[['EXECUTION DATE','TEST CASE NAME','EXECUTION STATUS','TOTAL NUMBER OF STEPS EXECUTED',
                                               'STEPS PASSED','EXECUTION TIME IN SECONDS',
                                               'EXECUTION START TIME',	'EXECUTION END TIME','DEFECTS LIST']]
    df.columns=['EXECUTION DATE',	'TEST CASE NAME',	'EXECUTION STATUS',	'TOTAL NUMBER OF STEPS EXECUTED',	'STEPS PASSED',	'EXECUTION TIME IN SECONDS',	'EXECUTION START TIME',	'EXECUTION END TIME',	'DEFECT SUMMARY']
    df_html = df.to_html(index=False)

    if random_id is None:
        random_id = 'id%d' % np.random.choice(np.arange(1000000))

    if style is None:
        style = """
        <style>
            table#{random_id} {{color:#000000;border-collapse: collapse;font-size: 16px;font:IBMPlexSans;text-align:center}}
            th{{background-color:#98AFC7;border: 1px solid black; border-collapse: collapse;text-align:center}}
        </style>
        """.format(random_id=random_id)
    else:
        new_style = []
        s = re.sub(r'</?style>', '', style).strip()
        for line in s.split('\n'):
                line = line.strip()
                if not re.match(r'^table', line):
                    line = re.sub(r'^', 'table ', line)
                new_style.append(line)
        new_style = ['<style>'] + new_style + ['</style>']

        style = re.sub(r'table(#\S+)?', 'table#%s' % random_id, '\n'.join(new_style))

    df_html = re.sub(r'<table', r'<table id=%s ' % random_id, df_html)
    print("html report created successfully")

    return(style + df_html)

# df = pd.read_excel("C:\\Users\\LIJAMANNATHARA\PycharmProjects\\UIAF_UI\\test results\\Result_2020-03-12_11-13-05\\TestResult.xlsx",sheet_name='summary report',index=False)
#
# HTML_with_style(df)