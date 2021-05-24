
import pandas as pd
class comparevalue():
	def execute(stepData,testDataExcel):
		try:
			x=stepData.split(",")
			print(testDataExcel)
			print(x)
			stepData1 = comparevalue.get_data_from_excel_sheet(testDataExcel,"Output",x[0],0)
			stepData2 = comparevalue.get_data_from_excel_sheet(testDataExcel,"Output",x[1],0)
			print(stepData1,stepData2)
			strstatus=None
			if stepData1 == stepData2:
			   strstatus="Passed%%Both the values are same"
			   print(strstatus)
			else:
			   strstatus="Failed%%Values do not match"
			return strstatus
		except Exception as e:
			print(e)

	def get_data_from_excel_sheet(excel, sheet, columnname, rowno):
		if excel:
			try:
				df = pd.read_excel(excel, skipinitialspace=True,
								   sheet_name=sheet, dtype=str)
			except Exception as e:
				print(e)
				#messagebox.showerror('Error', 'Incorrect excel')
			try:

				stepData = str(df.loc[rowno][columnname])

				return stepData
			except Exception as e:
				print(e)

		else:
			print("no excel")
			pass