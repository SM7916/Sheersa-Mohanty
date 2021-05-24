from openpyxl import load_workbook
import time
import os

def write(path,SheetName,Row,Column,Value):
    x=time.time()
    print(path)
    wb = load_workbook(path)
    if SheetName is None:
        sheet = wb.active
    else:
        sheet = wb[SheetName]
    sheet.cell(row=Row, column=Column).value = Value
    wb.save(path)
    wb.close()

