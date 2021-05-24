MsgBox("hhhh")
Set objExcel = CreateObject("Excel.Application")
Set objWorkbook = objExcel.Workbooks.Open("C:\\Users\\SoumenSaha\\Downloads\\Data.xlsx")
Set args = WScript.Arguments
objExcel.Application.Visible = False

objExcel.Cells(3, 1).Value = args(0)
objExcel.Cells(3, 2).Value = args(1)

objExcel.ActiveWorkbook.Save
objExcel.ActiveWorkbook.Close


objExcel.Application.Quit

WScript.Quit