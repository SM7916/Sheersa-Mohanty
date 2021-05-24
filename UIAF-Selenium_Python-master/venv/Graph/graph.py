import dash
import dash_core_components as dcc
import dash_html_components as html
import plotly.express as px
import plotly.graph_objs as go
import plotly.io as pio
import pandas as pd
import os
import dash_table
import Graph.main_for_api
import threading
from Graph.config_read import configRead
import dash_bootstrap_components as dbc

clrred = 'rgb(254,92,92)'
clrgrn = 'rgb(151,247,132)'
external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css','mycss.css']
# external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css',dbc.themes.BOOTSTRAP]
app = dash.Dash(__name__, external_stylesheets=external_stylesheets)
app.title = 'UIAF Report'

# assume you have a "long-form" data frame
# see https://plotly.com/python/px-arguments/ for more options
df = pd.read_excel(os.getcwd()+'/Graph/IBMhq_Execution_Summary_Report.xlsx',sheet_name='Summary Report')

try:
    mgr_options = df["PILLAR"].unique()
    mgr_options = [str(i) for i in mgr_options]
    mgr_options1 = df["MODULE"].unique()
    mgr_options1 = [str(i) for i in mgr_options1]
    mgr_options2 = df["SUB-MODULE"].unique()
    mgr_options2 = [str(i) for i in mgr_options2]
    columns = ['SL NO', 'PILLAR', 'MODULE', 'SUB-MODULE', 'EXECUTION DATE', 'TEST CASE NAME', 'ITERATION NO',
               'STEPS PASSED', 'DEFECTS LIST']
except Exception as e:
    mgr_options=["No Pillar Available"]
    mgr_options1 = ["No Module Available"]
    mgr_options2 = ["No Sub-Module Available"]
fig = go.Figure(data=[go.Scatter(x=[1, 2, 3], y=[4, 1, 2])])
app.layout =  html.Div([
html.H2(children="UIAF-Selenium Execution Summary",style={
            'textAlign': 'center',
            'color': '#636efa'}),
    html.Div([
        html.Div([

            dcc.Dropdown(
                id="PILLAR",
                placeholder="All Pillar",
                # clearable = False,
                options=[{
                    'label': i,
                    'value': i
                } for i in mgr_options],
                value='All PILLAR')
        ], className="three columns"),

        html.Div([

            dcc.Dropdown(
                id="MODULE",
                placeholder="All Module",
                options=[],
                # clearable = False,
                value='All MODULE', disabled=True)
        ], className="three columns"),
        html.Div([

            dcc.Dropdown(
                id="SUB_MODULE",
                # clearable = False,
                placeholder="All Sub module",
                options=[],
                value='All SUB MODULE', disabled=True)
        ], className="three columns"),
        html.Div([

            dcc.Dropdown(
                id="PassFail",
                # clearable = False,
                placeholder="All Status",
                options=[{"label":"Pass","value":"Pass"},{"label":"Fail","value":"Fail"}],
                value='All Status')
        ], className="three columns"),

    ], className="row"),


    html.Div([
        html.Div([

            dcc.Graph(id='pie chart')
        ],style={"width": "300px"}, className="two columns"),

        html.Div([

            dcc.Graph(id='funnel-graph')
        ], className="eight columns"),
    ], className="row"),


    html.Div(
        [
dash_table.DataTable(
    id='table',
style_cell={'textAlign': 'left'},
style_header={
        'backgroundColor': '#636efa',
        'fontWeight': 'bold',
'color': 'white',
    'textAlign':'center'
    },
style_data_conditional=[
    {
        'if': {
            'row_index': 'even',  # number | 'odd' | 'even'

        },
        'backgroundColor': '#e7e8e9',

    },
{
        'if': {
            'column_id': 'DEFECTS LIST'
        },
        'text-decoration': 'underline',
    'color':'blue'

    },
 {
        'if': { 'column_id': 'EXECUTION STATUS',
                'filter_query': '{EXECUTION STATUS} =Pass'},

        'backgroundColor': '#c9f6cd',
'textAlign':'center',



    },
    {
        'if': {'column_id': 'EXECUTION STATUS',
               'filter_query': '{EXECUTION STATUS} =Fail'},

        'backgroundColor': '#f7879b',
'textAlign':'center'

    },



] ,
),
dbc.Modal(
            [
                dbc.ModalHeader("Defect Details!"),
                dbc.ModalBody(html.Div([
dash_table.DataTable(id="popupTable",style_cell={'textAlign': 'left'},
style_header={
        'backgroundColor': '#636efa',
        'fontWeight': 'bold',
'color': 'white',
    'textAlign':'center'
    },)],
   # style={"width": "600px","margin-left":'15vh'}
                )),
dbc.ModalFooter(
                    dbc.Button("Close", id="close-sm", className="ml-auto")
                )
            ],
            id="modal",
            is_open=False,
size="lg"

        ),

        ],
    ),

])


# @app.callback(
#     dash.dependencies.Output("modal", "is_open"),
#     dash.dependencies.Output('popupTable', 'columns'),
#     dash.dependencies.Output('popupTable', 'data'),
#     [dash.dependencies.Input("table", "active_cell")],
#     [dash.dependencies.State("modal", "is_open")],
# [dash.dependencies.State("table", "data")]
# )
# def toggle_modal(active_cell, is_open,data):
#     if active_cell and (active_cell['column_id']=='DEFECTS LIST'):
#         print(active_cell)
#         active_cell_row_index = (active_cell['row'])
#         active_cell_column_index = (active_cell['column'])
#         active_cell_value =str(data[active_cell_row_index]['DEFECTS LIST'])
#         print(active_cell_value)
#         print(active_cell_column_index)
#         print(active_cell_row_index)
#         if active_cell_value!='':
#             df=pd.read_excel(os.getcwd()+'/IBMhq_Execution_Summary_Report.xlsx',sheet_name='Defect',dtype=str)
#             df=df[df['DEFECT ID'] ==active_cell_value]
#             df=df[['DEFECT ID','BROWSER','TESTCASE NAME','STEP NO','STEP DESCRIPTION','SUMMARY']]
#             columns = [{"name": i, "id": i} for i in df.columns]
#             data = df.to_dict('records')
#
#             return not is_open,columns,data

@app.callback(
    dash.dependencies.Output("modal", "is_open"),
    dash.dependencies.Output('popupTable', 'columns'),
    dash.dependencies.Output('popupTable', 'data'),
    [dash.dependencies.Input("table", "active_cell")],
    [dash.dependencies.Input("close-sm", "n_clicks")],


    [dash.dependencies.State("modal", "is_open")],
[dash.dependencies.State("table", "data")]
)
def toggle_modal(active_cell,close, is_open,data):
    if (active_cell and (active_cell['column_id']=='DEFECTS LIST')) or close:
        print(active_cell)
        active_cell_row_index = (active_cell['row'])
        active_cell_column_index = (active_cell['column'])
        active_cell_value =str(data[active_cell_row_index]['DEFECTS LIST'])
        print(active_cell_value)
        print(active_cell_column_index)
        print(active_cell_row_index)
        if active_cell_value!='':
            df=pd.read_excel(os.getcwd()+'/IBMhq_Execution_Summary_Report.xlsx',sheet_name='Defect',dtype=str)
            df=df[df['DEFECT ID'] ==active_cell_value]
            df=df[['DEFECT ID','BROWSER','TESTCASE NAME','STEP NO','STEP DESCRIPTION','SUMMARY']]
            columns = [{"name": i, "id": i} for i in df.columns]
            data = df.to_dict('records')

            return not is_open,columns,data
    return is_open,[],[]



@app.callback(
dash.dependencies.Output('MODULE', 'options'),
dash.dependencies.Output('MODULE', 'value'),
dash.dependencies.Output('MODULE', 'disabled'),
[dash.dependencies.Input('PILLAR', 'value')]
)
def p_dropdown_change(p):




    dfp=pd.read_excel(os.getcwd()+'/IBMhq_Execution_Summary_Report.xlsx',sheet_name='Summary Report')

    if p is None:
        p = "All PILLAR"
    #df = pd.read_excel(os.getcwd() + '/graph.xlsx', sheet_name='Summary Report')
    allp = "All PILLAR"
    if (p.lower() != allp.lower()):
        dfp = dfp[dfp['PILLAR'] == p]
        mod_list= dfp["MODULE"].unique()
        mod_list=[str(i) for i in mod_list]
        options1 = [{'label': i,
                                        'value': i
                                    } for i in mod_list]
        return options1,"ALL MODULE",False
    else:
        return [],"ALL MODULE",True

@app.callback(
dash.dependencies.Output('SUB_MODULE', 'options'),
dash.dependencies.Output('SUB_MODULE', 'value'),
dash.dependencies.Output('SUB_MODULE', 'disabled'),

[dash.dependencies.Input('PILLAR', 'value')],[dash.dependencies.Input('MODULE', 'value')]
)

def m_dropdown_change(p,m):
    dfm=pd.read_excel(os.getcwd()+'/IBMhq_Execution_Summary_Report.xlsx',sheet_name='Summary Report')
    if m is None:
        m = "ALL MODULE"

    #df = pd.read_excel(os.getcwd() + '/graph.xlsx', sheet_name='Summary Report')
    allm = "ALL MODULE"
    if (m.lower() != allm.lower()):
        dfm = dfm.loc[(dfm['PILLAR'] == p) & (dfm['MODULE'] == m)]
        sm_list = dfm['SUB-MODULE'].unique()
        sm_list = [str(i) for i in sm_list]

        options2 = [{
            'label': i,
            'value': i} for i in sm_list]
        return options2, "All SUB MODULE", False,
    else :
        return [],"All SUB MODULE",True



@app.callback(
    dash.dependencies.Output('funnel-graph', 'figure'),
    dash.dependencies.Output('pie chart', 'figure'),
    dash.dependencies.Output('table','columns'),
    dash.dependencies.Output('table','data'),
    dash.dependencies.Output('PILLAR','options'),



    [dash.dependencies.Input('PILLAR', 'value')],[dash.dependencies.Input('MODULE', 'value')],[dash.dependencies.Input('SUB_MODULE', 'value')],[dash.dependencies.Input('PassFail', 'value')])
def update_graph(Pillar,mod,submod,status):

    df = pd.read_excel(os.getcwd() + '/Graph/IBMhq_Execution_Summary_Report.xlsx', sheet_name='Summary Report',dtype=str)
    mgr_options = df["PILLAR"].unique()
    mgr_options = [str(i) for i in mgr_options]
    options_p = [{
        'label': i,
        'value': i} for i in mgr_options]

    df_plot,df_bar_plot,df_table=find_df(Pillar,mod,submod,df)
    pv = pd.pivot_table(
    df_bar_plot,
    index=['PILLAR'],
    columns=["Status"],
    values=["Amt"],
    aggfunc=sum,
    fill_value=0)
    if status=='Pass':
        df_plot = df_plot[df_plot['Status'] == "Pass"]
        df_bar_plot = df_bar_plot[df_bar_plot['Status'] == "Pass"]
        df_table = df_table[df_table['EXECUTION STATUS'] == "Pass"]


    if status=='Fail':
        df_plot = df_plot[df_plot['Status'] == "Fail"]
        df_bar_plot = df_bar_plot[df_bar_plot['Status'] == "Fail"]
        df_table = df_table[df_table['EXECUTION STATUS'] == "Fail"]


    clrred = '#f7464a'
    clrgrn = '#00af00'
    clrblue='#0084FF'
    trace1 = go.Bar(x=pv.index, y=pv[('Amt','Pass')], name='Pass',marker=dict(color=clrgrn))
    trace2 = go.Bar(x=pv.index, y=pv[('Amt', 'Fail')], name='Fail',marker=dict(color=clrred))
    trace3 = go.Bar(x=pv.index, y=pv[('Amt', 'Total')], name='Total', marker=dict(color=clrblue))
    df_plot = df_plot[df_plot["Status"] != 'Total']

    fig = go.Figure(data=[go.Pie(labels=df_plot['Status'], values=df_plot['Amt'],marker={'colors':[clrgrn,
        clrred]}, textinfo='label+percent',
                                 insidetextorientation='radial'
                                 )])
    if status=="Fail":
        fig = go.Figure(data=[go.Pie(labels=df_plot['Status'], values=df_plot['Amt'], marker={'colors': [
                                                                                                         clrred]},
                                     textinfo='label+percent',
                                     insidetextorientation='radial'
                                     )])


    #table
    columns = [{"name": i, "id": i} for i in df_table.columns]

    print(columns)
    data = df_table.to_dict('records')

    lis=[trace3,trace1, trace2]
    if status=="Pass":
        lis=[trace3,trace1]
    if status=="Fail":
        lis=[trace3,trace2]
    return {
    'data': lis,
    'layout':
        go.Layout(
            title='EXECUTION STATUS ',
            barmode='group',
        yaxis={'tickformat': ',d'}),

    },fig,columns,data,options_p



# g=0
# @app.callback(
# dash.dependencies.Output('drill', 'figure'),
# [dash.dependencies.Input('funnel-graph', 'clickData')]
# )
# def drill_down_graph_bar(c):
#     global g
#     g=g+1
#     print(c)
#     return go.Figure(data=[
#         go.Bar(
#             x=['x1'+str(g), 'x2'],
#             y=[1, 2]
#         )
#     ])



def find_df(p,m,sm,df):
 try:
    df_bar=df
    if p is None:
        p="All PILLAR"
    if m is None:
        m="ALL MODULE"
    if sm is None:
        sm="All SUB MODULE"
    allp="All PILLAR"
    allm="ALL MODULE"
    allsm="All SUB MODULE"
    #Pie Chart
    df=df[['PILLAR','MODULE','SUB-MODULE','EXECUTION DATE','TEST CASE NAME','ITERATION NO','EXECUTION STATUS','EXECUTION TIME IN SECONDS','STEPS PASSED','DEFECTS LIST']]
    df = df.applymap(str)

    if (m.lower() ==allm.lower() and sm.lower()==allsm.lower()) :
        if p.lower() == allp.lower():
            df_plot = df.copy()
        else:
            df_plot = df[df['PILLAR'] == p]

    elif m.lower()==allm.lower() :
        df_plot = df[df['SUB-MODULE'] == sm]

        if p == "All PILLAR":
            df_plot = df_plot.copy()
        else:
            df_plot = df_plot[df_plot['PILLAR'] == p]
    elif sm.lower()==allsm.lower() :
        df_plot = df[df['MODULE'] == m]
        if p.lower() == allp.lower():
            df_plot = df_plot.copy()
        else:
            df_plot = df_plot[df_plot['PILLAR'] == p]
    else:
        df_plot = df[df['MODULE'] == m]
        df_plot = df_plot[df_plot['SUB-MODULE'] == sm]

        if p == "All PILLAR":
            df_plot = df_plot.copy()
        else:
            df_plot = df_plot[df_plot['PILLAR'] == p]
    df_table=df_plot

    df=df_plot[['PILLAR','EXECUTION STATUS']]
    df_new = pd.DataFrame(columns=['PILLAR','Status','Amt'])
    pillar_list = df["PILLAR"].unique()
    pillar_list = [str(i) for i in pillar_list]
    for i in pillar_list:
        df = df.applymap(str)
        len_pass=len(df.loc[(df['PILLAR'] == i) &(df['EXECUTION STATUS'] == "Pass")])
        len_fail = len(df.loc[(df['PILLAR'] == i) & (df['EXECUTION STATUS'] == "Fail")])
        total=len_fail+len_pass
        pass_data=[i,"Pass",len_pass]
        fail_data=[i,"Fail",len_fail]
        total_data = [i, "Total", total]
        length=len(df_new)
        df_new.loc[length] = pass_data
        df_new.loc[length+1] = fail_data
        df_new.loc[length + 2] = total_data




    #bar chart
    if (m.lower() == allm.lower() and sm.lower() == allsm.lower() and p.lower() == allp.lower()):

            df_bar_final=df_new.copy()
    elif (m.lower() == allm.lower() and sm.lower() == allsm.lower() and (p.lower() != allp.lower())):
        df_bar = df_bar.applymap(str)
        temp_df=df_bar.loc[(df_bar['PILLAR'] == p)]
        mod_list=temp_df["MODULE"].unique()
        mod_list = [str(i) for i in mod_list]
        df_new_mod= pd.DataFrame(columns=['PILLAR','Status','Amt'])
        for i in mod_list:
            len_pass = len(temp_df.loc[(temp_df['MODULE'] == i) & (temp_df['EXECUTION STATUS'] == "Pass")])
            len_fail = len(temp_df.loc[(temp_df['MODULE'] == i) & (temp_df['EXECUTION STATUS'] == "Fail")])
            total=len_fail+len_pass
            pass_data = [i, "Pass", len_pass]
            fail_data = [i, "Fail", len_fail]
            total_data = [i, "Total", total]
            length = len(df_new_mod)
            df_new_mod.loc[length] = pass_data
            df_new_mod.loc[length + 1] = fail_data
            df_new_mod.loc[length + 2] = total_data
        df_bar_final=df_new_mod.copy()
    elif ((m.lower() != allm.lower()) and sm.lower() == allsm.lower() and (p.lower() != allp.lower()) ):
        df_bar = df_bar.applymap(str)
        temp_df_sm=df_bar.loc[(df_bar['PILLAR'] == p) & (df_bar['MODULE'] == m)]
        smod_list=temp_df_sm["SUB-MODULE"].unique()
        smod_list = [str(i) for i in smod_list]
        df_new_smod= pd.DataFrame(columns=['PILLAR','Status','Amt'])
        for i in smod_list:
            len_pass = len(temp_df_sm.loc[(temp_df_sm['SUB-MODULE'] == i) & (temp_df_sm['EXECUTION STATUS'] == "Pass")])
            len_fail = len(temp_df_sm.loc[(temp_df_sm['SUB-MODULE'] == i) & (temp_df_sm['EXECUTION STATUS'] == "Fail")])
            total=len_fail+len_pass
            pass_data = [i, "Pass", len_pass]
            fail_data = [i, "Fail", len_fail]
            total_data=[i,"Total",total]
            length = len(df_new_smod)
            df_new_smod.loc[length] = pass_data
            df_new_smod.loc[length + 1] = fail_data
            df_new_smod.loc[length + 2] = total_data

        df_bar_final=df_new_smod.copy()
    else:
        df_bar_final=df_new.copy()

    slno=[item for item in range(1,len(df_table)+1)]
    df_table.insert(loc=0, column="SL NO", value=slno)
    df_table= df_table.replace({'nan': ''}, regex=True)
    df_table=df_table.applymap(str)


    return df_new,df_bar_final,df_table
 except Exception as e:

     pass


def run1():
    Host = configRead('host')
    Port = configRead('port_for_report')

    app.run_server(debug=False,host=Host,port=Port)
def run2():
    Graph.main_for_api.appRun()




def graphRun1():
    t1 = threading.Thread(target=run1)
    t1.start()

def graphRun2():
    t2 = threading.Thread(target=run2)
    t2.start()
    