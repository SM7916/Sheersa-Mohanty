# #
# # from  ActionClasses.WriteToParticularCellInExcel import write
# # from tkinter import *
# # from tkinter import ttk, messagebox
# # import tkinter as tk
# # from PIL import ImageTk, Image
# # from functools import partial
# # from pandastable.core import Table
# # from pandastable.data import TableModel
# # import pandas as pd
# # from docx import Document
# #
# # import docx2txt
# # from ctypes import windll, pointer, wintypes
# # import os
# # from openpyxl import load_workbook
# #
# # windll.shcore.SetProcessDpiAwareness(1)
# # import hqdriver
# # from tkinter.filedialog import asksaveasfilename, askopenfilename
# # from py4j.java_gateway import JavaGateway
# # import subprocess
# # from  ActionClasses.portkiller import port_killer
# #
# # dir_path = os.path.dirname(os.path.realpath(__file__))
# # # Here, we are creating our class, Window, and inheriting from the Frame
# # # class. Frame is a class from the tkinter module. (see Lib/tkinter/__init__)
# # class Window(Frame):
# #     gateway = JavaGateway()
# #     app = gateway.entry_point
# #     var=testDataObject=testMngtdObject=SheetFromComponentCombo=SheetFromDataCombo=SheetFromObjectCombo=SheetFromScriptsCombo=execFile=execObject=testSetObject=testSetFile=testMngtFile=generateTestFile=generateTestObject=testScriptSelectedFile=testScriptSelectedObject=resuableComponentFile=resuableComponentObject=objectFile=objectObject=testDataFile=None
# #     resuableComponentSheet,objectSheet,testSetSheet,testMngtSheet,execSheet,generateSheet = 'Reusable Components','AllObjects','Master','defecttoolinfo','Summary','Connection'
# #     isOpenGS=isOpenTS=isOpenRC=isOpenOR=isOpenTD=isOpenMTS=isOpenTM=isOpenET=False
# #     texts1script = {}
# #     texts1Resuable={}
# #     texts1Object={}
# #     texts2script = {}
# #     texts2Resuable = {}
# #     texts2Object = {}
# #     texts1Data={}
# #     texts2Data={}
# #     texts2Mngt={}
# #     texts2Set={}
# #     texts2Exec={}
# #     texts3Mngt={}
# #     texts2Generate={}
# #
# #     # Define settings upon initialization. Here you can specify
# #     def __init__(self, master=None):
# #         Frame.__init__(self, master)
# #         self.topbar_colour = "#d5d5d5"
# #         self.master = master
# #         self.init_window()
# #
# #     def init_window(self):
# #         self.configure(bg="white")
# #         self.pack(fill=BOTH, expand=1)
# #         box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
# #         box.place(x=333, y=66)
# #         box.update()
# #
# #         file = open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\a.txt", 'r')
# #         text = file.read()
# #         Window.labb = Label(box, text=text, anchor="w", justify='left', bg="white", padx=30,
# #                             pady=30)
# #         Window.labb.place(x=0, y=0)
# #         Window.labb.pack()
# #         menu = Menu(self.master)
# #         menu = Menu(self.master)
# #         self.master.config(menu=menu)
# #         menu.config(title="bharath")
# #         file = Menu(menu, tearoff=0)
# #         space = Menu(menu)
# #         space.add_separator()
# #         file.add_command(label="Exit", command=self.client_exit)
# #         menu.add_cascade(label="File", menu=file)
# #         Configure = Menu(menu, tearoff=0)
# #         Configure.add_command(label="Undo")
# #         menu.add_cascade(label="Configure", menu=Configure)
# #         About = Menu(menu, tearoff=0)
# #         menu.add_command(label="About", command=self.About)
# #         root.update()
# #         self.update()
# #         print(root.winfo_height())
# #         label = Frame(self, bg="#1163F7", highlightbackground="#1163F7", borderwidth=0, relief="groove", width=333,
# #                       height=root.winfo_height())
# #         if root.update():
# #             label = Frame(self, bg="#1163F7", highlightbackground="#1163F7", borderwidth=0, relief="groove", width=333,
# #                           height=root.winfo_height())
# #         label.update()
# #         label.grid(row=0, column=0)
# #         label.grid_columnconfigure(0, weight=1)
# #         label.grid_propagate(False)
# #         frame = tk.Frame(label, background='white', borderwidth=0, relief='sunken')
# #         frame.grid(row=0, column=0, sticky='nesw', ipadx=60)
# #         frame.grid_columnconfigure(0, weight=1)
# #         self.b = tk.Button(frame, text="", state='disabled', bg="#d5d5d5")
# #         self.b.grid(row=0, column=0, sticky='nesw', ipady=10)
# #         frame = Label(self, fg="white", text="@Copyright IBM Corp. 2020 All Rights Reserved.", background='#1163F7',
# #                       borderwidth=0, relief='sunken', width=40, anchor="w", font=('IBMPlexSans', 7), padx=6)
# #         frame.place(x=0, y=self.winfo_height() - 30)
# #         frame = Label(self, fg="white", text="@Copyright IBM Corp. 2020 All Rights Reserved.", background='#1163F7',
# #                       borderwidth=0, relief='sunken', width=40, anchor="w", font=('IBMPlexSans', 7), padx=6)
# #         frame.place(x=0, y=self.winfo_height() - 30)
# #         frame = Frame(label, background='white', borderwidth=0.5, relief='sunken')
# #         frame.grid(row=3, column=0, sticky='nesw', ipadx=66)
# #         frame.grid_columnconfigure(0, weight=1)
# #         photo1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image3.png"))
# #         t = ToggledFrame(frame, image=photo1, text='  Manage Test Scripts ', relief="raised", bg="#1163F7")
# #         t.grid(row=3, column=0, sticky='nesw')
# #         Window.x1 = t.sub_frame
# #         self.sub1 = tk.Label(t.sub_frame, text="Test Scripts", bg="white", fg="#1163F7", anchor='w', padx=60,
# #                              font=('IBMPlexSans', 9))
# #         self.sub1.bind('<Button>', self.mouseClick1)
# #         self.sub1.grid(ipadx=66, ipady=10, sticky='nesw')
# #         self.sub2 = tk.Label(t.sub_frame, text="Reusable Components", bg="white", fg="#1163F7", anchor='w', padx=60,
# #                              font=('IBMPlexSans', 9))
# #         self.sub2.bind("<Button>", self.mouseClick2)
# #         self.sub2.grid(ipadx=66, ipady=10, sticky='nesw')
# #         self.sub3 = tk.Label(t.sub_frame, text="Object Repository", bg="white", fg="#1163F7", anchor='w', padx=60,
# #                              font=('IBMPlexSans', 9))
# #         self.sub3.bind("<Button>", self.mouseClick3)
# #         self.sub3.grid(ipadx=66, ipady=10, sticky='nesw')
# #         self.sub4 = tk.Label(t.sub_frame, text="Test Data", bg="white", fg="#1163F7", anchor='w', padx=60,
# #                              font=('IBMPlexSans', 9))
# #         self.sub4.bind("<Button>", self.mouseClick4)
# #         self.sub4.grid(ipadx=66, ipady=10, sticky='nesw')
# #         frame = Frame(label, background='white', borderwidth=0.5, relief='sunken')
# #         frame.grid(row=7, column=0, sticky='nesw', ipadx=66)
# #         frame.grid_columnconfigure(0, weight=1)
# #         photo1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image6.png"))
# #         t = ToggledFrame(frame, image=photo1, text=' Test Execution/Reporting ', relief="raised", bg="#1163F7")
# #         # self.t.config(bg='white')
# #         t.grid(row=6, column=0, sticky='nesw')
# #         Window.x2 = t.sub_frame
# #         self.sub5 = tk.Label(t.sub_frame, text="Execute Tests", bg="white", fg="#1163F7", anchor='w', padx=60,
# #                              font=('IBMPlexSans', 9))
# #         self.sub5.bind("<Button>", self.mouseClick5)
# #         self.sub5.grid(ipadx=100, ipady=10, sticky='nesw')
# #         self.sub6 = tk.Label(t.sub_frame, text="View Test Report", bg="white", fg="#1163F7", anchor='w', padx=60,
# #                              font=('IBMPlexSans', 9))
# #         self.sub6.bind("<Button>", self.mouseClick6)
# #         self.sub6.grid(ipadx=100, ipady=10, sticky='nesw')
# #         button_name = {"": "", "Record Flows": self.Record_Flows, "Generate Tests": self.Generate_Tests,
# #                        "Manage Test Scripts": "", "Maintain Test Sets": self.Maintain_Test_Sets,
# #                        "Test Management": self.Test_Management}
# #         for i in range(1, 6):
# #             if i is 3:
# #                 continue
# #             self.color = "#1163F7"
# #             frame = Frame(label, background='white', borderwidth=0, relief="flat")
# #             frame.grid(row=i, column=0, sticky='nesw', ipadx=66)
# #             frame.grid_columnconfigure(0, weight=1)
# #             img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image" + str(i) + ".png"
# #             photo1 = ImageTk.PhotoImage(Image.open(img))
# #             text = "   " + list(button_name)[i]
# #             self.b = Button(frame, image=photo1, compound="left", text=text, anchor='w', highlightbackground="white",
# #                             borderwidth=1, highlightthickness=2, relief="groove", bg=self.color, fg="white", height=20,
# #                             padx=15, font=("IBMPlexSans", 9))  # anchor for text justification
# #             self.b.config(command=partial(button_name[list(button_name)[i]], self.b, self.color))
# #             self.b.grid(row=i, column=0, sticky='nesw', ipady=20)
# #             self.b.image = photo1
# #         texts2 = {}
# #         self.new_layout(texts2)
# #         self.name = None
# #         self.texts = None
# #         self.high = None
# #     def display_workbook(self, lab, excel_name):
# #         excel_name = excel_name.split("/")[-1]
# #         workbook_name = Label(lab, text=excel_name, bg="#d5d5d5", fg="black", anchor="w", padx=20,
# #                               pady=10)
# #         workbook_name.config(height=1, width=29)
# #         workbook_name.place(x=0, y=1)
# #     def About(self):
# #         Window.labb.forget()
# #         texts = {}
# #         self.new_layout(texts)
# #         box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
# #         box.place(x=333, y=66)
# #         box.update()
# #         with open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\About.txt", 'r') as f:
# #             text = f.read()
# #         Window.labb = Label(box, text=text, anchor="w", justify='left', bg="white", padx=30, pady=30)
# #         Window.labb.place(x=0, y=0)
# #         Window.labb.pack()
# #     def Record_Flows(self, b, color):
# #         global saved_file, already_saved
# #         try:
# #             if saved_file and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         saved_file = ""
# #         Window.labb.forget()
# #         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #         box.place(x=333, y=66)
# #         box.update()
# #         global high, img, Record
# #         texts = {}
# #         if high:
# #             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
# #             high.image = img
# #             Window.x1.forget()
# #             Window.x2.forget()
# #             Record = False
# #         high = b
# #         self.color = color
# #         self.new_layout(texts)
# #         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\rec.png"
# #         photo1 = ImageTk.PhotoImage(Image.open(img))
# #         bg = tk.Button(self.header_label, image=photo1, compound='left', text=" Record", anchor='center', bg="#005CFB",
# #                        fg="white", width=125, height=30, borderwidth=0,command=self.record_Button_func)
# #         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image11.png"
# #         photo2 = ImageTk.PhotoImage(Image.open(img))
# #         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
# #         bg.place(x=root.winfo_width() - 150, y=15)
# #         bg.image = photo1
# #         b.image = photo2
# #         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image1.png"))
# #     def record_Button_func(self):
# #         path='"C:\\Program Files (x86)\\SAP\\FrontEnd\\SapGui\\saplogon.exe"'
# #         subprocess.Popen(path)
# #         Window.app = Window.gateway.entry_point
# #         val=Window.app.SAP_GUI_SELECT_ADVANCE_WINLIST_ITEM_hqdriver('ES1')
# #         pro=os.path.dirname(dir_path) + '/SAP/UIAF_PRO_RECORDER/app.publish/UIAF Pro.exe'
# #         subprocess.Popen(pro)
# #     def Generate_Tests(self, b, color):
# #         global saved_file, already_saved
# #         try:
# #             if saved_file and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         saved_file = ""
# #         Window.labb.forget()
# #         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #         box.place(x=333, y=66)
# #         box.update()
# #         global high, img, Generate
# #         if high:
# #             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
# #             high.image = img
# #             Window.x1.forget()
# #             Window.x2.forget()
# #             Generate = False
# #         high = b
# #         self.color = color
# #         img =os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image22.png"
# #         photo2 = ImageTk.PhotoImage(Image.open(img))
# #         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
# #         b.image = photo2
# #         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image2.png"))
# #         if self.isOpenGS:
# #             self.texts2Generate = {"Generate Script": self.generate_Script_func,
# #                                    "Save": lambda file=self.generateTestFile,
# #                                                   sheet=self.generateSheet: self.generateTestObject.save(filename=file,
# #                                                                                                          sheetName=sheet),
# #                                    "Open": self.open_Generate_Scripts}
# #         else:
# #             self.texts2Generate = {"Generate Script": self.generate_Script_func, "Save":self.save_no_sheet, "Open": self.open_Generate_Scripts}
# #         self.new_layout(self.texts2Generate)
# #     def generate_Script_func(self):
# #             Window.app = Window.gateway.entry_point
# #             Window.app.generate_Script()
# #     def Maintain_Test_Sets(self, b, color):
# #         global saved_file, already_saved,saved_sheet,saved_object
# #         try:
# #             if saved_file and saved_file is not self.testSetFile and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         if (self.testSetFile):
# #             Window.File_Open(self, self.testSetFile,self.testSetSheet,None)
# #             saved_file = self.testSetFile
# #             already_saved = False
# #         else:
# #             saved_file = False
# #             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #             box.place(x=333, y=66)
# #             box.update()
# #         global high, img, Maintain_test_set
# #         if high:
# #             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
# #             high.image = img
# #             Window.x1.forget()
# #             Window.x2.forget()
# #             Maintain_test_set = False
# #         high = b
# #         self.color = color
# #         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image44.png"
# #         photo2 = ImageTk.PhotoImage(Image.open(img))
# #         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
# #         b.image = photo2
# #         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image4.png"))
# #         if self.isOpenMTS:
# #             self.texts2Set = {"Delete Row": self.testSetObject.deleteRow, "Add Row": self.testSetObject.addRow,
# #                               "Add WorkBook": "",
# #                               "Save As": lambda type='Master_', sheet=self.testSetSheet: self.testSetObject.saveAs(
# #                                   type=type, sheetName=sheet),
# #                               "Save": lambda file=self.testSetFile, sheet=self.testSetSheet: self.testSetObject.save(
# #                                   filename=file, sheetName=sheet),
# #                               "Open": self.open_Test_Sets}
# #         else:
# #             self.texts2Set = {"Delete Row":self.save_no_sheet, "Add Row":self.save_no_sheet, "Add WorkBook":self.save_no_sheet, "Save As":self.save_no_sheet, "Save":self.save_no_sheet,
# #                   "Open": self.open_Test_Sets}
# #         self.new_layout(self.texts2Set)
# #         if saved_file:
# #             self.display_workbook(self.header_label, saved_file)
# #     def Test_Management(self, b, color):
# #         global saved_file, already_saved
# #         try:
# #             if saved_file and saved_file is not self.testMngtFile and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         if (self.testMngtFile):
# #             self.testMngtdObject = Window.File_Open(self, self.testMngtFile, self.testMngtSheet,None)
# #             saved_file = self.testMngtFile
# #             already_saved = False
# #         else:
# #             saved_file = False
# #             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #             box.place(x=333, y=66)
# #             box.update()
# #         global high, img, Test_management
# #         if high:
# #             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
# #             high.image = img
# #             Window.x1.forget()
# #             Window.x2.forget()
# #             Test_management = False
# #         high = b
# #         self.color = color
# #         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image55.png"
# #         photo2 = ImageTk.PhotoImage(Image.open(img))
# #         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
# #         b.image = photo2
# #         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image5.png"))
# #         if self.isOpenTM:
# #             self.texts2Mngt = {
# #                 "Save": lambda file=self.testMngtFile, sheet=self.testMngtSheet: self.testMngtdObject.save(
# #                     filename=file,
# #                     sheetName=sheet),
# #                 "Open": self.open_Test_Mngt}
# #         else:
# #             self.texts2Mngt = {"Save":self.save_no_sheet, "Open": self.open_Test_Mngt}
# #         self.new_layout(self.texts2Mngt)
# #         lab = Label(self.header_label, text=" Test Management Tool", bg=self.topbar_colour, fg="black")
# #         lab.pack()
# #         lab.place(x=360, y=2)
# #         self.texts3Mngt = {"ALM": "", "JIRA": "", "NA": ""}
# #         self.new_checkBox(self.texts3Mngt)
# #         CheckVar = IntVar()
# #         self.C = Checkbutton(self.header_label, text="Create Defects Automatically", variable=CheckVar, onvalue=1,
# #                              offvalue=0,
# #                              bg=self.topbar_colour, fg="black")
# #         self.C.pack()
# #         self.C.place(x=650, y=30)
# #         if saved_file:
# #             self.display_workbook(self.header_label, saved_file)
# #     def createbox(self):
# #         box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
# #         box.place(x=333, y=66)
# #         box.update()
# #     def mouseClick1(self, event):
# #         global saved_file, already_saved
# #         size = [20]
# #         pos = [360]
# #         global saved_file
# #         try:
# #             if saved_file and saved_file is not self.testScriptSelectedFile and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         if (self.testScriptSelectedFile):
# #             Window.File_Open(self, self.testScriptSelectedFile, None,None)
# #             wb = load_workbook(self.testScriptSelectedFile)
# #             sheetNames = wb.sheetnames
# #             saved_file = self.testScriptSelectedFile
# #             already_saved = False
# #         else:
# #             box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
# #             box.place(x=333, y=66)
# #             box.update()
# #             self.texts1script = {"Select Script Sheet": ["Select Script Sheet"]}
# #         if self.name:
# #             self.name.configure(fg="#1163F7", text=self.texts, anchor='w', font=('IBMPlexSans', 9))
# #         self.name = self.sub1
# #         self.texts = self.sub1['text'].strip("-    ")
# #         self.sub1.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + self.texts, anchor='w',
# #                             bg="white")
# #         root.update()
# #         if self.isOpenTS:
# #             self.texts2script = {"Delete Row": self.testScriptSelectedObject.deleteRow,
# #                                  "Add Row": self.testScriptSelectedObject.addRow, "Add WorkBook": "",
# #                                  "Save As": lambda type='Script_',
# #                                                    sheet=self.SheetFromScriptsCombo: self.testScriptSelectedObject.saveAs(
# #                                      type=type, sheetName=sheet),
# #                                  "Save": lambda file=self.testScriptSelectedFile,
# #                                                 sheet=self.SheetFromScriptsCombo: self.testScriptSelectedObject.save(
# #                                      filename=file, sheetName=sheet),
# #                                  "Open": self.open_Test_Scripts}
# #         else:
# #             self.texts2script = {"Delete Row":self.save_no_sheet, "Add Row":self.save_no_sheet, "Add WorkBook":self.save_no_sheet, "Save As":self.save_no_sheet, "Save":self.save_no_sheet,
# #                   "Open": self.open_Test_Scripts}
# #
# #         self.new_layout(self.texts2script)
# #         self.new_label(self.texts1script, size, pos,file=self.testScriptSelectedFile,obj=self.testScriptSelectedObject)
# #         if saved_file:
# #             self.display_workbook(self.header_label, saved_file)
# #     def mouseClick2(self, event):
# #         global saved_file, already_saved
# #         try:
# #             if saved_file and saved_file is not self.resuableComponentFile and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         if (self.resuableComponentFile):
# #             Window.File_Open(self, self.resuableComponentFile, None,None)
# #             saved_file = self.resuableComponentFile
# #             already_saved = False
# #         else:
# #             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #             box.place(x=333, y=66)
# #             box.update()
# #             self.texts1Resuable = {"Select the Component Sheet":["Select the Component Sheet"]}
# #         if self.name:
# #             self.name.configure(fg="#1163F7", text=self.texts, anchor='w', font=('IBMPlexSans', 9))
# #         self.name = self.sub2
# #         self.texts = self.sub2['text'].strip("-    ")
# #         self.sub2.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + self.texts, anchor='w',
# #                             bg="white")
# #         root.update()
# #         if self.isOpenRC:
# #             self.texts2Resuable = {"Delete Row": self.resuableComponentObject.deleteRow,
# #                                    "Add Row": self.resuableComponentObject.addRow, "Add WorkBook": "",
# #                                    "Save As": lambda type='Reusable_',
# #                                                      sheet=self.SheetFromComponentCombo: self.resuableComponentObject.saveAs(
# #                                        type=type, sheetName=sheet),
# #                                    "Save": lambda file=self.resuableComponentFile,
# #                                                   sheet=self.SheetFromComponentCombo: self.resuableComponentObject.save(
# #                                        filename=file, sheetName=sheet), "Open": self.open_Reusable_Components}
# #         else:
# #             self.texts2Resuable = {"Delete Row":self.save_no_sheet, "Add Row":self.save_no_sheet, "Add WorkBook":self.save_no_sheet, "Save As":self.save_no_sheet,
# #                   "Save":self.save_no_sheet, "Open": self.open_Reusable_Components}
# #         c = self.new_layout(self.texts2Resuable)
# #         size = [25]
# #         pos = [360]
# #         self.new_label(self.texts1Resuable, size, pos,file=self.resuableComponentFile,obj=self.resuableComponentObject)
# #         if saved_file:
# #             self.display_workbook(self.header_label, saved_file)
# #
# #     def mouseClick3(self, event):
# #         global saved_file, already_saved
# #         try:
# #             if saved_file and saved_file is not self.objectFile and not already_saved:
# #                 self.save_file()
# #         except:
# #             pass
# #         if (self.objectFile):
# #             Window.File_Open(self, self.objectFile, None,None)
# #             saved_file = self.objectFile
# #             already_saved = False
# #         else:
# #             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #             box.place(x=333, y=66)
# #             box.update()
# #         if self.name:
# #             self.name.configure(fg="#1163F7", text=self.texts, anchor='w', font=('IBMPlexSans', 9))
# #         self.name = self.sub3
# #         self.texts = self.sub3['text'].strip("-    ")
# #         self.sub3.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + self.texts, anchor='w',
# #                             bg="white")
# #         if self.isOpenOR:
# #             self.texts2Object = {"Add Column": self.objectObject.addColumn, "Delete Row": self.objectObject.deleteRow,
# #                                  "Add Row": self.objectObject.addRow, "Add WorkBook": "",
# #                                  "Save As": lambda type='Script_',
# #                                                    sheet=self.SheetFromObjectCombo: self.objectObject.saveAs(type=type,
# #                                                                                                              sheetName=sheet),
# #                                  "Save": lambda file=self.objectFile,
# #                                                 sheet=self.SheetFromObjectCombo: self.objectObject.save(filename=file,
# #                                                                                                         sheetName=sheet),
# #                                  "Open": self.open_Object_Repository}
# #         else:
# #             self.texts2Object = {"Add Column":self.save_no_sheet, "Delete Row":self.save_no_sheet, "Add Row":self.save_no_sheet, "Add WorkBook":self.save_no_sheet, "Save As":self.save_no_sheet,
# #                   "Save":self.save_no_sheet, "Open": self.open_Object_Repository}
# #         self.new_layout(self.texts2Object)
# #         self.texts1Object = {"Select the Objects Excel": ["Select the Objects Excel"]}
# #         size = [23]
# #         pos = [360]
# #         self.new_label(self.texts1Object, size, pos,file=self.resuableComponentFile,obj=self.resuableComponentObject)
# #         if saved_file:
# #             self.display_workbook(self.header_label, saved_file)
# #
# #     def mouseClick4(self, event):
# #         global saved_file, already_saved
# #         try:
# #             if saved_file and saved_file is not self.testDataFile and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         if (self.testDataFile):
# #             Window.File_Open(self, self.testDataFile, None,None)
# #             saved_file = self.testDataFile
# #             already_saved = False
# #         else:
# #             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #             box.place(x=333, y=66)
# #             box.update()
# #         if self.name:
# #             self.name.configure(fg="#1163F7", text=self.texts, anchor='w', font=('IBMPlexSans', 9))
# #         self.name = self.sub4
# #         self.texts = self.sub4['text'].strip("-    ")
# #         root.update()
# #         self.sub4.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + self.texts, anchor='w',
# #                             bg="white")
# #         if self.isOpenTD:
# #             self.texts2Data = {"Delete Column": self.testDataObject.deleteColumn,
# #                                "Add Column": self.testDataObject.addColumn, "Delete Row": self.testDataObject.deleteRow,
# #                                "Add Row": self.testDataObject.addRow,
# #                                "Add WorkBook": "",
# #                                "Save As": lambda type='TestData_',
# #                                                  sheet=self.SheetFromDataCombo: self.testDataObject.saveAs(type=type,
# #                                                                                                            sheetName=sheet),
# #                                "Save": lambda file=self.testDataFile,
# #                                               sheet=self.SheetFromDataCombo: self.testDataObject.save(
# #                                    filename=file, sheetName=sheet), "Open": self.open_Test_Data}
# #         else:
# #             self.texts2Data = {"Delete Column":self.save_no_sheet, "Add Column":self.save_no_sheet, "Delete Row":self.save_no_sheet, "Add Row":self.save_no_sheet, "Add WorkBook":self.save_no_sheet,
# #                   "Save As":self.save_no_sheet, "Save":self.save_no_sheet, "Open": self.open_Test_Data}
# #         self.new_layout(self.texts2Data)
# #         self.texts1Data = {"Select the Test Data Sheet": ["Select the Test Data Sheet"]}
# #         size = [23]
# #         pos = [360]
# #         self.new_label(self.texts1Data, size, pos,file=self.testDataFile,obj=self.testDataObject)
# #         if saved_file:
# #             self.display_workbook(self.header_label, saved_file)
# #     def mouseClick5(self, event):
# #         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #         box.place(x=333, y=66)
# #         box.update()
# #         if self.name:
# #             self.name.configure(fg="#1163F7", text=self.texts, anchor='w', font=('IBMPlexSans', 9))
# #         self.name = self.sub5
# #         self.texts = self.sub5['text'].strip("-    ")
# #         self.sub5.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + self.texts, anchor='w',
# #                             bg="white")
# #         root.update()
# #         if self.isOpenET:
# #             self.texts2Exec = {"Execute Test": hqdriver.hqDriver.execute, "Delete Row": self.execObject.deleteRow,
# #                                "Add Row": self.execObject.addRow,
# #                                "Save": lambda file=self.execFile, sheet=self.execSheet: self.execObject.save(
# #                                    filename=file, sheetName=sheet), "Open Driver": self.open_Test_Execution}
# #         else:
# #             self.texts2Exec = {"Execute Test":hqdriver.hqDriver.execute, "Delete Row": self.save_no_sheet, "Add Row":self.save_no_sheet, "Save":self.save_no_sheet,"Open Driver":self.open_Test_Execution }
# #         self.new_layout(self.texts2Exec)
# #
# #     def mouseClick6(self, event):
# #         global saved_file, already_saved
# #         try:
# #             if saved_file and not already_saved:
# #                 self.save_file(saved_file)
# #         except:
# #             pass
# #         saved_file = ""
# #         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
# #         box.place(x=333, y=66)
# #         box.update()
# #         if self.name:
# #             self.name.configure(fg="#1163F7", text=self.texts, anchor='w', font=('IBMPlexSans', 9))
# #         self.name = self.sub6
# #         self.texts = self.sub6['text'].strip("-    ")
# #         root.update()
# #         self.sub6.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + self.texts, anchor='w',
# #                             bg="white")
# #         texts2 = {}
# #         self.new_layout(texts2)
# #         root1 = os.path.dirname(dir_path) + '\\test results'
# #         dirlist = [item for item in os.listdir(root1) if os.path.isdir(os.path.join(root1, item))]
# #         texts1 = {" Report Format": dirlist}
# #         self.size = [25]
# #         self.pos = [360]
# #         for i in range(len(texts1)):
# #          self.var = tk.StringVar()
# #          self.comboExample = ttk.Combobox(textvariable=self.var, state="readonly",
# #                                          values=[i for i in texts1[list(texts1)[i]]], width=self.size[i])
# #          self.comboExample.set(list(texts1)[i])
# #          self.comboExample.pack()
# #          self.comboExample.bind("<<ComboboxSelected>>",
# #                                lambda event, file1=None, obj1=None: self.comboBoxAction6(file1, obj1, event))
# #          self.comboExample.place(x=self.pos[i], y=20)
# #
# #     def client_exit(self):
# #         on_closing()
# #
# #     def open_Test_Scripts(self):
# #
# #         global saved_file, already_saved
# #         self.testScriptSelectedFile = askopenfilename(
# #             initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
# #             filetypes=[("Excel files", "Script_*.xlsx .xls")])
# #         if self.testScriptSelectedFile:
# #             self.isOpenTS = True
# #             wb = load_workbook(self.testScriptSelectedFile)
# #             sheetNames = wb.sheetnames
# #             size = [20]
# #             pos = [360]
# #             self.texts1script = {sheetNames[0]: sheetNames}
# #             self.SheetFromScriptsCombo = sheetNames[0]
# #             saved_file = self.testScriptSelectedFile
# #             already_saved = False
# #             self.testScriptSelectedObject = Window.File_Open(self, self.testScriptSelectedFile, None,None)
# #             self.texts2script = {"Delete Row":self.testScriptSelectedObject.deleteRow, "Add Row":self.testScriptSelectedObject.addRow, "Add WorkBook": "", "Save As":lambda type='Script_',sheet=self.SheetFromScriptsCombo:self.testScriptSelectedObject.saveAs(type=type,sheetName=sheet),
# #                                  "Save": lambda file=self.testScriptSelectedFile,
# #                                                 sheet=self.SheetFromScriptsCombo: self.testScriptSelectedObject.save(
# #                                      filename=file, sheetName=sheet),
# #                                  "Open": self.open_Test_Scripts}
# #             self.new_layout(self.texts2script)
# #             self.new_label(self.texts1script, size, pos, file=self.testScriptSelectedFile,
# #                            obj=self.testScriptSelectedObject)
# #             self.display_workbook(self.header_label, saved_file)
# #         else:
# #             pass
# #
# #     def open_Reusable_Components(self):
# #
# #         global saved_file, already_saved
# #         size = [25]
# #         pos = [360]
# #         self.resuableComponentFile = askopenfilename(
# #             initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
# #             filetypes=[("Excel files", "Reusable_Components*.xlsx .xls")])
# #         if self.resuableComponentFile:
# #             self.isOpenRC = True
# #             wb = load_workbook(self.resuableComponentFile)
# #             sheetNames = wb.sheetnames
# #             self.texts1Resuable = {sheetNames[0]: sheetNames}
# #             self.SheetFromComponentCombo = sheetNames[0]
# #             saved_file = self.resuableComponentFile
# #             already_saved = False
# #             self.resuableComponentObject = Window.File_Open(self, self.resuableComponentFile, None,None)
# #             self.texts2Resuable = {"Delete Row":self.resuableComponentObject.deleteRow, "Add Row":self.resuableComponentObject.addRow, "Add WorkBook": "", "Save As":lambda type='Reusable_',sheet=self.SheetFromComponentCombo:self.resuableComponentObject.saveAs(type=type,sheetName=sheet),
# #                                    "Save": lambda file=self.resuableComponentFile,
# #                                                   sheet=self.SheetFromComponentCombo: self.resuableComponentObject.save(
# #                                        filename=file, sheetName=sheet), "Open": self.open_Reusable_Components}
# #             self.new_layout(self.texts2Resuable)
# #             self.new_label(self.texts1Resuable, size, pos, file=self.resuableComponentFile,
# #                            obj=self.resuableComponentObject)
# #             self.display_workbook(self.header_label, saved_file)
# #         else:
# #             pass
# #
# #     def open_Object_Repository(self):
# #
# #          global saved_file, already_saved
# #          size = [23]
# #          pos = [360]
# #          self.objectFile = askopenfilename(
# #              initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
# #              filetypes=[("Excel files", "Script_*.xlsx .xls")])
# #          if self.objectFile:
# #             self.isOpenOR = True
# #             wb = load_workbook(self.objectFile)
# #             sheetNames = wb.sheetnames
# #             self.texts1Object = {sheetNames[0]: sheetNames}
# #             self.SheetFromObjectCombo=sheetNames[0]
# #             saved_file = self.objectFile
# #             already_saved = False
# #             sheet = self.objectSheet
# #          # self.display_workbook(saved_file)
# #             self.objectObject = Window.File_Open(self, self.objectFile,sheet,None)
# #             self.texts2Object = {"Add Column":self.objectObject.addColumn, "Delete Row":self.objectObject.deleteRow, "Add Row":self.objectObject.addRow, "Add WorkBook": "", "Save As":lambda type='Script_',sheet=self.SheetFromObjectCombo:self.objectObject.saveAs(type=type,sheetName=sheet),
# #                               "Save": lambda file=self.objectFile,
# #                                              sheet=self.SheetFromObjectCombo: self.objectObject.save(filename=file,
# #                                                                                                      sheetName=sheet),
# #                               "Open": self.open_Object_Repository}
# #             self.new_layout(self.texts2Object)
# #             self.new_label(self.texts1Object, size, pos, file=self.objectFile,
# #                         obj=self.objectObject)
# #             self.display_workbook(self.header_label, saved_file)
# #          else:
# #             pass
# #
# #     def open_Test_Data(self):
# #
# #         global saved_file, already_saved
# #         size = [23]
# #         pos = [360]
# #         self.testDataFile = askopenfilename(
# #             initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
# #             filetypes=[("Excel files", "TestData_*.xlsx .xls")])
# #         if self.testDataFile:
# #             self.isOpenTD = True
# #
# #             wb = load_workbook(self.testDataFile)
# #             sheetNames = wb.sheetnames
# #             self.SheetFromDataCombo=sheetNames[0]
# #             self.texts1Data = {sheetNames[0]: sheetNames}
# #             saved_file = self.testDataFile
# #             already_saved = False
# #             self.testDataObject = Window.File_Open(self, self.testDataFile, None,None)
# #             self.texts2Data = {"Delete Column": self.testDataObject.deleteColumn, "Add Column":self.testDataObject.addColumn, "Delete Row": self.testDataObject.deleteRow, "Add Row":self.testDataObject.addRow,
# #                                "Add WorkBook": "",
# #                                "Save As":lambda type='TestData_',sheet=self.SheetFromDataCombo:self.testDataObject.saveAs(type=type,sheetName=sheet),
# #                                "Save": lambda file=self.testDataFile,
# #                                               sheet=self.SheetFromDataCombo: self.testDataObject.save(
# #                                    filename=file, sheetName=sheet), "Open": self.open_Test_Data}
# #             self.new_layout(self.texts2Data)
# #             self.new_label(self.texts1Data, size, pos, file=self.testDataFile,
# #                            obj=self.testDataObject)
# #             self.display_workbook(self.header_label, saved_file)
# #         else:
# #             pass
# #
# #     def open_Test_Sets(self):
# #
# #         global saved_file, already_saved, display
# #         self.testSetFile = askopenfilename(initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
# #                                            filetypes=[("Excel files", "Master_*.xlsx .xls")])
# #         if self.testSetFile:
# #             self.isOpenMTS = True
# #             saved_file = self.testSetFile
# #             already_saved = False
# #             self.testSetObject = Window.File_Open(self, self.testSetFile, self.testSetSheet,None)
# #             self.texts2Set = {"Delete Row": self.testSetObject.deleteRow, "Add Row":self.testSetObject.addRow, "Add WorkBook": "", "Save As":lambda type='Master_',sheet=self.testSetSheet:self.testSetObject.saveAs(type=type,sheetName=sheet),
# #                               "Save": lambda file=self.testSetFile, sheet=self.testSetSheet: self.testSetObject.save(
# #                                   filename=file, sheetName=sheet),
# #                               "Open": self.open_Test_Sets}
# #             self.new_layout(self.texts2Set)
# #             self.display_workbook(self.header_label, saved_file)
# #         else:
# #             pass
# #     def open_Generate_Scripts(self):
# #
# #         global saved_file, already_saved, display
# #         self.generateTestFile = os.path.dirname(dir_path) + '\\SAP\\TestData\\config.xlsx'
# #         if self.generateTestFile:
# #             self.isOpenGS = True
# #             saved_file = self.generateTestFile
# #             already_saved = False
# #             self.generateTestObject = Window.File_Open(self, self.generateTestFile, self.generateSheet,None)
# #
# #             self.texts2Generate = {"Generate Script": self.generate_Script_func, "Save":lambda file=self.generateTestFile,sheet=self.generateSheet:self.generateTestObject.save(filename=file,sheetName=sheet),
# #                                    "Open": self.open_Generate_Scripts}
# #
# #             self.new_layout(self.texts2Generate)
# #             self.display_workbook(self.header_label, saved_file)
# #         else:
# #             pass
# #
# #     def open_Test_Mngt(self):
# #
# #         global saved_file, already_saved, display
# #         dir_path = os.path.dirname(os.path.realpath(__file__))
# #         self.testMngtFile = os.path.dirname(dir_path) + '\\SAP\\TestData\\Execution.xlsx'
# #         if self.testMngtFile:
# #             self.isOpenTM = True
# #             saved_file = self.testMngtFile
# #             already_saved = False
# #             self.texts2Mngt = {
# #                 "Save": lambda file=self.testMngtFile, sheet=self.testMngtSheet: self.testMngtdObject.save(filename=file,
# #                                                                                               sheetName=sheet),
# #                 "Open": self.open_Test_Mngt}
# #             self.new_layout(self.texts2Mngt)
# #             lab = Label(self.header_label, text=" Test Management Tool", bg=self.topbar_colour, fg="black")
# #             lab.pack()
# #             lab.place(x=360, y=2)
# #             self.texts3Mngt = {"ALM": "", "JIRA": "", "NA": ""}
# #             self.new_checkBox(self.texts3Mngt)
# #             CheckVar = IntVar()
# #             self.C = Checkbutton(self.header_label, text="Create Defects Automatically", variable=CheckVar, onvalue=1,
# #                                  offvalue=0,
# #                                  bg=self.topbar_colour, fg="black",command=partial(self.selected_checkBox,CheckVar))
# #             self.C.pack()
# #             self.C.place(x=650, y=30)
# #             self.testMngtdObject = Window.File_Open(self, self.testMngtFile, self.testMngtSheet,None)
# #             self.display_workbook(self.header_label, saved_file)
# #         else:
# #             pass
# #
# #     def open_Test_Execution(self):
# #         self.isOpenET = True
# #         global saved_file, already_saved, display
# #         dir_path = os.path.dirname(os.path.realpath(__file__))
# #         self.execFile = os.path.dirname(dir_path) + '/SAP/TestData/Execution.xlsx'
# #         saved_file = self.execFile
# #         already_saved = False
# #         self.execObject = Window.File_Open(self, self.execFile,self.execSheet,None)
# #         self.texts2Exec = {"Execute Test": hqdriver.hqDriver.execute, "Delete Row":self.execObject.deleteRow, "Add Row":self.execObject.addRow,
# #                            "Save":lambda file=self.execFile,sheet=self.execSheet:self.execObject.save(filename=file,sheetName=sheet), "Open Driver": self.open_Test_Execution}
# #         self.new_layout(self.texts2Exec)
# #         self.display_workbook(self.header_label, saved_file)
# #
# #     def File_Open(self, file, sheet,box):
# #         try:
# #             if os.path.exists(file):
# #                 if not box:
# #                     box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width() - 345, bg="white",
# #                                 padx=3,
# #                                 pady=3)
# #                     box.place(x=333, y=66)
# #                     box.update()
# #                 else:
# #                     box = box
# #                 a = MyTable
# #                 pt = a.make_table(box, file, sheet, height=box.winfo_height() - 70, width=box.winfo_width() - 75)
# #             return pt
# #         except:
# #             pass
# #
# #     def save_file(self, saved_file):
# #         global already_saved
# #         MsgBox = tk.messagebox.askquestion('Save Excel', 'Save before leaving',
# #                                            icon='warning')
# #         if MsgBox == 'yes':
# #             already_saved = True
# #             print(saved_file)
# #         else:
# #             already_saved = False
# #             tk.messagebox.CANCEL()
# #
# #     def new_layout(self, texts2):
# #         self.header_label = tk.Frame(root, bg=self.topbar_colour, width=root.winfo_width(), height=10)
# #         self.header_label.pack(pady=0)
# #         self.header_label.place(x=0, y=0, height=66)
# #         x = 0
# #         for i in range(len(texts2)):
# #             x += 150
# #             bg = tk.Button(self.header_label, text=list(texts2)[i], borderwidth=0, highlightbackground="white",
# #                            highlightthickness=0, anchor='center', bg="#005CFB", fg="white", width=13,
# #                            command=texts2[list(texts2)[i]])
# #             bg.place(x=root.winfo_width() - x, y=15)
# #
# #     def new_label(self, texts1, size, pos,file=None,obj=None):
# #         for i in range(len(texts1)):
# #             self.var=tk.StringVar()
# #             self.comboExample = ttk.Combobox(textvariable=self.var, state="readonly",
# #                                              values=[i for i in texts1[list(texts1)[i]]], width=size[i])
# #             self.comboExample.set(list(texts1)[i])
# #             self.comboExample.pack()
# #             self.comboExample.bind("<<ComboboxSelected>>", lambda event,file1=file,obj1=obj :self.comboBoxAction(file1,obj1,event))
# #             self.comboExample.place(x=pos[i], y=20)
# #
# #     def comboBoxAction(self,file,obj,event=None):
# #         if event:
# #             if obj == self.testDataObject:
# #                 self.SheetFromDataCombo=self.var.get()
# #             elif obj==self.objectObject:
# #                 self.SheetFromObjectCombo=self.var.get()
# #             elif obj==self.resuableComponentObject:
# #                 self.SheetFromComponentCombo=self.var.get()
# #             elif obj==self.testScriptSelectedObject:
# #
# #                 self.SheetFromScriptsCombo=self.var.get()
# #             print("comboBoxAction:",self.var.get())
# #             obj = Window.File_Open(self, file, self.var.get(),None)
# #
# #     def comboBoxAction6(self,file,obj,event=None):
# #         global show
# #         if event:
# #             print(self.var.get())
# #             print(file,obj)
# #             show= self.var.get()
# #             box1 = Frame(self, height=root.winfo_height() - 116, width=(root.winfo_width() - 335) / 2, bg="white",
# #                          padx=3,
# #                          pady=3, highlightthickness=1, highlightbackground='black')
# #             box1.place(x=333, y=116)
# #             box1.update()
# #             root1 = os.path.dirname(dir_path) + '\\test results'
# #             open_this = root1 + '\\' + show +'\\TestResult.xlsx'
# #             add = int(root.winfo_width() - 330) / 2
# #             box2 = Frame(self, height=root.winfo_height() - 116, width=(root.winfo_width() - 335) / 2, bg="white", padx=3,
# #                      pady=3, highlightthickness=1, highlightbackground='black')
# #             box2.place(x=333 + add, y=116)
# #             box2.update()
# #             box11 = Frame(self, height=50, width=box1.winfo_width(), bg="white", padx=3,
# #                       pady=3, highlightthickness=1, highlightbackground='black')
# #             box11.place(x=333, y=66)
# #             box22 = Frame(self, height=50, width=box2.winfo_width(), bg="white", padx=3,
# #                       pady=3, highlightthickness=1, highlightbackground='black')
# #             box22.place(x=334 + add, y=66)
# #             lable1 = Label(box11, text="Summary Report", fg='black', bg='white', font=("IBMPlexSans", 12))
# #             lable1.place(x=333, y=10)
# #             lable2 = Label(box22, text="Detailed Summary", fg='black', bg='white', font=("IBMPlexSans", 12))
# #             lable2.place(x=333, y=10)
# #             Window.File_Open(self, open_this, 'summary report', box1)
# #             Window.File_Open(self, open_this, 'summary', box2)
# #
# #     def selected_checkBox(self,CheckVar1):
# #         if CheckVar1.get():
# #             write(self.testMngtFile, self.testMngtSheet, 8, 2, "TRUE")
# #             self.testMngtdObject.model.setValueAt("TRUE", 6, 1)
# #             self.testMngtdObject.drawText(6, 1, "TRUE")
# #         else:
# #             write(self.testMngtFile, self.testMngtSheet, 8, 2, "FALSE")
# #             self.testMngtdObject.model.setValueAt("FALSE", 6, 1)
# #             self.testMngtdObject.drawText(6, 1, "FALSE")
# #
# #     def selected_radio(self, Checkvar1):
# #         if Checkvar1.get()=="JIRA":
# #             write(self.testMngtFile, self.testMngtSheet, 9, 2, "JIRA")
# #             self.testMngtdObject.model.setValueAt("JIRA",7,1)
# #             self.testMngtdObject.drawText(7,1,"JIRA")
# #         elif Checkvar1.get()=="ALM":
# #             write(self.testMngtFile,self.testMngtSheet, 9, 2, "ALM")
# #             self.testMngtdObject.model.setValueAt("ALM", 7, 1)
# #             self.testMngtdObject.drawText(7, 1, "ALM")
# #         else:
# #             write(self.testMngtFile, self.testMngtSheet, 9, 2, "NA")
# #             self.testMngtdObject.model.setValueAt("NA", 7, 1)
# #             self.testMngtdObject.drawText(7 , 1, "NA")
# #
# #     def new_checkBox(self, texts3):
# #         x = 360
# #         CheckVar1 = StringVar()
# #         for i in range(len(texts3)):
# #             self.C = Radiobutton(self.header_label, text=list(texts3)[i], var=CheckVar1, value=list(texts3)[i],
# #                                  bg=self.topbar_colour, fg="black", tristatevalue=0,
# #                                  command=partial(self.selected_radio, CheckVar1))
# #             self.C.pack()
# #             self.C.place(x=x, y=30)
# #             x = x + 66
# #
# #     def save_no_sheet(self):
# #         messagebox.showinfo("Error", "Please Select the  Sheet")
# #
# # class MyTable(Table):
# #     def __init__(self, parent=None, **kwargs):
# #         Table.__init__(self, parent, **kwargs)
# #         return
# #
# #     def saveAs(self,type,sheetName=None):
# #         """Save dataframe to file"""
# #         filename = asksaveasfilename(initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
# #                                          filetypes=[("Excel files", type+"*.xlsx .xls")], defaultextension=".xlsx",
# #                                          initialfile=type)
# #         if filename:
# #             self.model.save(filename)
# #             self.filename = filename
# #             self.currentdir = os.path.basename(filename)
# #         return
# #
# #     def save(self, filename, sheetName=None):
# #         """Save current file"""
# #         print("save:",filename,sheetName)
# #         if filename:
# #             self.model.save(filename, sheetName)
# #             self.filename = filename
# #             self.currentdir = os.path.basename(filename)
# #         # else:
# #         #     messagebox.showinfo("Error", "Please Select the  Sheet")
# #         return
# #
# #     def make_table(frame, file, sheet, **kwds):
# #         """make a sample table"""
# #         wb = load_workbook(file)
# #         sheetNames = wb.sheetnames
# #         excel = file
# #         if sheet:
# #             if sheet not in sheetNames:
# #                 df = pd.read_excel(excel, skipinitialspace=True, encoding='utf-8')
# #             else:
# #                 df = pd.read_excel(excel, sheet_name=sheet, skipinitialspace=True, encoding='utf-8')
# #         else:
# #             df = pd.read_excel(excel, skipinitialspace=True, encoding='utf-8')
# #         pt = MyTable(frame, dataframe=df, **kwds)
# #         pt.editable = True
# #
# #         pt.show()
# #         return pt
# #
# # class ToggledFrame(tk.Frame):
# #     def __init__(self, parent, bg, image, text="", *args, **options):
# #         tk.Frame.__init__(self, parent, *args, **options)
# #         self.title_frame = ttk.Frame(self)
# #         self.title_frame.pack()
# #         self.color = bg
# #         self.toggle_button = tk.Button(self.title_frame, image=image, compound="left", text=" " + text, anchor='w',
# #                                        bg=self.color, fg="white", height=20,
# #                                        highlightbackground="black", borderwidth=0, padx=15, font=("IBMPlexSans", 9))
# #         self.toggle_button.config(
# #             command=partial(lambda *args: self.toggle(self.toggle_button, self.color, image), self.toggle_button))
# #         self.toggle_button.grid(row=3, column=0, ipady=20, sticky='nesw', ipadx=66)
# #         self.toggle_button.image = image
# #         self.sub_frame = tk.Frame(self, relief="sunken")
# #
# #     def toggle(self, button, color, image):
# #         global high, x, img, Manage_test_script
# #         if high and high is not button:
# #             high.config(fg="white", image=img, font=("IBMPlexSans", 9), bg=self.color)
# #             high.image = img
# #             self.sub_frame.forget()
# #         high = button
# #         self.color = color
# #         img = image
# #         txt = button.config('text')[-1][:-1]
# #         if button.cget('bg') in ["#1163F7"]:
# #             if txt.strip() == "Manage Test Scripts":
# #                 img1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image33.png"))
# #             else:
# #                 img1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image66.png"))
# #             button.config(fg="#1163F7", image=img1, compound='left', font=('IBMPlexSans', 9, 'bold'), bg="white")
# #             button.image = img1
# #             self.sub_frame.pack()
# #             root.update()
# #             self.header_label = tk.Frame(root, bg='#d5d5d5', width=root.winfo_width(), height=10)
# #             self.header_label.pack(pady=0)
# #             self.header_label.place(x=0, y=0, height=66)
# #         else:
# #             button.config(fg="white", image=img, compound='left', font=('IBMPlexSans', 9), bg=self.color)
# #             button.image = img
# #             self.sub_frame.forget()
# #             root.update()
# #             self.header_label = tk.Frame(root, bg="#d5d5d5", width=root.winfo_width(), height=10)
# #             self.header_label.pack(pady=0)
# #             self.header_label.place(x=0, y=0, height=66)
# #
# # class GradientFrame(tk.Canvas):
# #     '''A gradient frame which uses a canvas to draw the background'''
# #     def __init__(self, parent, color1="red", color2="black", **kwargs):
# #         tk.Canvas.__init__(self, parent, **kwargs)
# #         self._color1 = color1
# #         self._color2 = color2
# #         self.bind("<Configure>", self._draw_gradient)
# #
# #     def _draw_gradient(self, event=None):
# #         '''Draw the gradient'''
# #         self.delete("gradient")
# #         width = self.winfo_width()
# #         height = self.winfo_height()
# #         limit = 1000
# #         (r1, g1, b1) = self.winfo_rgb(self._color1)
# #         (r2, g2, b2) = self.winfo_rgb(self._color2)
# #         r_ratio = float(r2 - r1) / limit
# #         g_ratio = float(g2 - g1) / limit
# #         b_ratio = float(b2 - b1) / limit
# #         for i in range(height):
# #             nr = int(r1 + (r_ratio * i))
# #             ng = int(g1 + (g_ratio * i))
# #             nb = int(b1 + (b_ratio * i))
# #             color = "#%4.4x%4.4x%4.4x" % (nr, ng, nb)
# #             self.create_line(0, i, height, i, tags=("gradient",), fill=color)
# #         self.lower("gradient")
# #
# # def on_closing():
# #     if messagebox.askokcancel("Quit","Do you want to exit?"):
# #         port_killer()
# #         root.destroy()
# #
# # def center(e):
# #     w = int(root.winfo_width() / 5)  # get root width and scale it ( in pixels )
# #     s = 'IBM UI4AutomationFrameworks'.rjust(w // 2)
# #     root.title(s)
# #
# #
# # root = Tk()
# # c=os.system("taskkill /f /im  excel.exe")
# # print(c)
# # c=os.system("taskkill /f /im  winword.exe")
# # print(c)
# # root.bind("<Configure>", center)
# # root.state('zoomed')
# # root.iconbitmap(default=os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\Icon1.ico")
# # ExecutionFile = os.path.dirname(dir_path) + '/SAP/TestData/Execution.xlsx'
# # write(ExecutionFile, "defecttoolinfo", 8, 2, "FALSE")
# # high, img = "", ""
# # x = False
# # dir_path = os.path.dirname(os.path.realpath(__file__))
# # Record, Generate, Manage_test_script, Maintain_test_set, Test_management, Test_execution = True, True, True, True, True, True
# # app = Window(root)
# # saved_file, already_saved = "", False
# # root.protocol("WM_DELETE_WINDOW",on_closing)
# # root.mainloop()
#
#
#
# from  ActionClasses.WriteToParticularCellInExcel import write
# from tkinter import *
# from tkinter import ttk, messagebox
# import tkinter as tk
# from PIL import ImageTk, Image
# from functools import partial
# from pandastable.core import Table
# from pandastable.data import TableModel
# import pandas as pd
# from docx import Document
#
# import docx2txt
# from ctypes import windll, pointer, wintypes
# import os
# from openpyxl import load_workbook
#
# windll.shcore.SetProcessDpiAwareness(1)
# import hqdriver
# from tkinter.filedialog import asksaveasfilename, askopenfilename
# from py4j.java_gateway import JavaGateway
# import subprocess
# from  ActionClasses.portkiller import port_killer
#
# dir_path = os.path.dirname(os.path.realpath(__file__))
# # Here, we are creating our class, Window, and inheriting from the Frame
# # class. Frame is a class from the tkinter module. (see Lib/tkinter/__init__)
# class Window(Frame):
#     gateway = JavaGateway()
#     app = gateway.entry_point
#     var=testDataObject=testMngtdObject=SheetFromComponentCombo=SheetFromDataCombo=SheetFromObjectCombo=SheetFromScriptsCombo=execFile=execObject=testSetObject=testSetFile=testMngtFile=generateTestFile=generateTestObject=testScriptSelectedFile=testScriptSelectedObject=resuableComponentFile=resuableComponentObject=objectFile=objectObject=testDataFile=None
#     resuableComponentSheet,objectSheet,testSetSheet,testMngtSheet,execSheet,generateSheet = 'Reusable Components','AllObjects','Master','defecttoolinfo','Summary','Connection'
#     isOpenGS=isOpenTS=isOpenRC=isOpenOR=isOpenTD=isOpenMTS=isOpenTM=isOpenET=False
#     texts1script = {}
#     texts1Resuable={}
#     texts1Object={}
#     texts2script = {}
#     texts2Resuable = {}
#     texts2Object = {}
#     texts1Data={}
#     texts2Data={}
#     texts2Mngt={}
#     texts2Set={}
#     texts2Exec={}
#     texts3Mngt={}
#     texts2Generate={}
#
#     # Define settings upon initialization. Here you can specify
#     def __init__(self, master=None):
#         Frame.__init__(self, master)
#         self.topbar_colour = "#d5d5d5"
#         self.master = master
#         self.init_window()
#
#     def init_window(self):
#         self.configure(bg="white")
#         self.pack(fill=BOTH, expand=1)
#         box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
#         box.place(x=333, y=66)
#         box.update()
#
#         file = open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\a.txt", 'r')
#         text = file.read()
#         Window.labb = Label(box, text=text, anchor="w", justify='left', bg="white", padx=30,
#                             pady=30)
#         Window.labb.place(x=0, y=0)
#         Window.labb.pack()
#         menu = Menu(self.master)
#         menu = Menu(self.master)
#         self.master.config(menu=menu)
#         menu.config(title="bharath")
#         file = Menu(menu, tearoff=0)
#         space = Menu(menu)
#         space.add_separator()
#         file.add_command(label="Exit", command=self.client_exit)
#         menu.add_cascade(label="File", menu=file)
#         Configure = Menu(menu, tearoff=0)
#         Configure.add_command(label="Undo")
#         menu.add_cascade(label="Configure", menu=Configure)
#         About = Menu(menu, tearoff=0)
#         menu.add_command(label="About", command=self.About)
#         root.update()
#         self.update()
#
#         label = Frame(self, bg="#1163F7", highlightbackground="#1163F7", borderwidth=0, relief="groove", width=333,
#                       height=root.winfo_height())
#         if root.update():
#             label = Frame(self, bg="#1163F7", highlightbackground="#1163F7", borderwidth=0, relief="groove", width=333,
#                           height=root.winfo_height())
#         label.update()
#         label.grid(row=0, column=0)
#         label.grid_columnconfigure(0, weight=1)
#         label.grid_propagate(False)
#         frame = tk.Frame(label, background='white', borderwidth=0, relief='sunken')
#         frame.grid(row=0, column=0, sticky='nesw', ipadx=60)
#         frame.grid_columnconfigure(0, weight=1)
#         self.b = tk.Button(frame, text="", state='disabled', bg="#d5d5d5")
#         self.b.grid(row=0, column=0, sticky='nesw', ipady=10)
#         frame = Label(self, fg="white", text="@Copyright IBM Corp. 2020 All Rights Reserved.", background='#1163F7',
#                       borderwidth=0, relief='sunken', width=40, anchor="w", font=('IBMPlexSans', 7), padx=6)
#         frame.place(x=0, y=self.winfo_height() - 30)
#         frame = Label(self, fg="white", text="@Copyright IBM Corp. 2020 All Rights Reserved.", background='#1163F7',
#                       borderwidth=0, relief='sunken', width=40, anchor="w", font=('IBMPlexSans', 7), padx=6)
#         frame.place(x=0, y=self.winfo_height() - 30)
#         frame = Frame(label, background='white', borderwidth=0.5, relief='sunken')
#         frame.grid(row=3, column=0, sticky='nesw', ipadx=66)
#         frame.grid_columnconfigure(0, weight=1)
#         photo1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image3.png"))
#         t = ToggledFrame(frame, image=photo1, text='  Manage Test Scripts ', relief="raised", bg="#1163F7")
#         t.grid(row=3, column=0, sticky='nesw')
#         Window.x1 = t.sub_frame
#         self.sub1 = tk.Label(t.sub_frame, text="Test Scripts", bg="white", fg="#1163F7", anchor='w', padx=60,
#                              font=('IBMPlexSans', 9))
#         self.sub1.bind('<Button>', self.mouseClick1)
#         self.sub1.grid(ipadx=66, ipady=10, sticky='nesw')
#         self.sub2 = tk.Label(t.sub_frame, text="Reusable Components", bg="white", fg="#1163F7", anchor='w', padx=60,
#                              font=('IBMPlexSans', 9))
#         self.sub2.bind("<Button>", self.mouseClick2)
#         self.sub2.grid(ipadx=66, ipady=10, sticky='nesw')
#         self.sub3 = tk.Label(t.sub_frame, text="Object Repository", bg="white", fg="#1163F7", anchor='w', padx=60,
#                              font=('IBMPlexSans', 9))
#         self.sub3.bind("<Button>", self.mouseClick3)
#         self.sub3.grid(ipadx=66, ipady=10, sticky='nesw')
#         self.sub4 = tk.Label(t.sub_frame, text="Test Data", bg="white", fg="#1163F7", anchor='w', padx=60,
#                              font=('IBMPlexSans', 9))
#         self.sub4.bind("<Button>", self.mouseClick4)
#         self.sub4.grid(ipadx=66, ipady=10, sticky='nesw')
#         frame = Frame(label, background='white', borderwidth=0.5, relief='sunken')
#         frame.grid(row=7, column=0, sticky='nesw', ipadx=66)
#         frame.grid_columnconfigure(0, weight=1)
#         photo1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image6.png"))
#         t = ToggledFrame(frame, image=photo1, text=' Test Execution/Reporting ', relief="raised", bg="#1163F7")
#         # self.t.config(bg='white')
#         t.grid(row=6, column=0, sticky='nesw')
#         Window.x2 = t.sub_frame
#         self.sub5 = tk.Label(t.sub_frame, text="Execute Tests", bg="white", fg="#1163F7", anchor='w', padx=60,
#                              font=('IBMPlexSans', 9))
#         self.sub5.bind("<Button>", self.mouseClick5)
#         self.sub5.grid(ipadx=100, ipady=10, sticky='nesw')
#         self.sub6 = tk.Label(t.sub_frame, text="View Test Report", bg="white", fg="#1163F7", anchor='w', padx=60,
#                              font=('IBMPlexSans', 9))
#         self.sub6.bind("<Button>", self.mouseClick6)
#         self.sub6.grid(ipadx=100, ipady=10, sticky='nesw')
#         button_name = {"": "", "Record Flows": self.Record_Flows, "Generate Tests": self.Generate_Tests,
#                        "Manage Test Scripts": "", "Maintain Test Sets": self.Maintain_Test_Sets,
#                        "Test Management": self.Test_Management}
#         for i in range(1, 6):
#             if i is 3:
#                 continue
#             self.color = "#1163F7"
#             frame = Frame(label, background='white', borderwidth=0, relief="flat")
#             frame.grid(row=i, column=0, sticky='nesw', ipadx=66)
#             frame.grid_columnconfigure(0, weight=1)
#             img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image" + str(i) + ".png"
#             photo1 = ImageTk.PhotoImage(Image.open(img))
#             text = "   " + list(button_name)[i]
#             self.b = Button(frame, image=photo1, compound="left", text=text, anchor='w', highlightbackground="white",
#                             borderwidth=1, highlightthickness=2, relief="groove", bg=self.color, fg="white", height=20,
#                             padx=15, font=("IBMPlexSans", 9))  # anchor for text justification
#             self.b.config(command=partial(button_name[list(button_name)[i]], self.b, self.color))
#             self.b.grid(row=i, column=0, sticky='nesw', ipady=20)
#             self.b.image = photo1
#         texts2 = {}
#         self.new_layout(texts2)
#         self.name = None
#         self.texts = None
#         self.high = None
#     def display_workbook(self, lab, excel_name):
#         excel_name = excel_name.split("/")[-1]
#         workbook_name = Label(lab, text=excel_name, bg="#d5d5d5", fg="black", anchor="w", padx=20,
#                               pady=10)
#         workbook_name.config(height=1, width=29)
#         workbook_name.place(x=0, y=1)
#     def About(self):
#         Window.labb.forget()
#         texts = {}
#         self.new_layout(texts)
#         box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
#         box.place(x=333, y=66)
#         box.update()
#         with open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\About.txt", 'r') as f:
#             text = f.read()
#         Window.labb = Label(box, text=text, anchor="w", justify='left', bg="white", padx=30, pady=30)
#         Window.labb.place(x=0, y=0)
#         Window.labb.pack()
#     def Record_Flows(self, b, color):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         try:
#             if name:
#               name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#               name=''
#         except:
#             pass
#         saved_file = ""
#         Window.labb.forget()
#         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#         box.place(x=333, y=66)
#         box.update()
#         global high, img, Record
#         texts = {}
#         if high:
#             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
#             high.image = img
#             Window.x1.forget()
#             Window.x2.forget()
#             Record = False
#         high = b
#         self.color = color
#         self.new_layout(texts)
#         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\rec.png"
#         photo1 = ImageTk.PhotoImage(Image.open(img))
#         bg = tk.Button(self.header_label, image=photo1, compound='left', text=" Record", anchor='center', bg="#005CFB",
#                        fg="white", width=125, height=30, borderwidth=0,command=self.record_Button_func)
#         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image11.png"
#         photo2 = ImageTk.PhotoImage(Image.open(img))
#         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
#         bg.place(x=root.winfo_width() - 150, y=15)
#         bg.image = photo1
#         b.image = photo2
#         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image1.png"))
#     def record_Button_func(self):
#         path='"C:\\Program Files (x86)\\SAP\\FrontEnd\\SapGui\\saplogon.exe"'
#         subprocess.Popen(path)
#         Window.app = Window.gateway.entry_point
#         val=Window.app.SAP_GUI_SELECT_ADVANCE_WINLIST_ITEM_hqdriver('ES1')
#         pro=os.path.dirname(dir_path) + '/SAP/UIAF_PRO_RECORDER/app.publish/UIAF Pro.exe'
#         subprocess.Popen(pro)
#     def Generate_Tests(self, b, color):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         try:
#             if name:
#               name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#               name=''
#         except:
#             pass
#         saved_file = ""
#         Window.labb.forget()
#         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#         box.place(x=333, y=66)
#         box.update()
#         global high, img, Generate
#         if high:
#             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
#             high.image = img
#             Window.x1.forget()
#             Window.x2.forget()
#             Generate = False
#         high = b
#         self.color = color
#         img =os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image22.png"
#         photo2 = ImageTk.PhotoImage(Image.open(img))
#         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
#         b.image = photo2
#         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image2.png"))
#         if self.isOpenGS:
#             self.texts2Generate = {"Generate Script": self.generate_Script_func,
#                                    "Save": lambda file=self.generateTestFile,
#                                                   sheet=self.generateSheet: self.generateTestObject.save(filename=file,
#                                                                                                          sheetName=sheet),
#                                    "Open": self.open_Generate_Scripts}
#         else:
#             self.texts2Generate = {"Generate Script": self.generate_Script_func, "Save":self.save_no_sheet, "Open": self.open_Generate_Scripts}
#         self.new_layout(self.texts2Generate)
#     def generate_Script_func(self):
#             Window.app = Window.gateway.entry_point
#             Window.app.generate_Script()
#     def Maintain_Test_Sets(self, b, color):
#         global saved_file, already_saved,saved_sheet,name,texts,save_object
#         try:
#             if saved_file and saved_file is not self.testSetFile and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         try:
#             if name:
#               name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#               name=''
#         except:
#             pass
#         if (self.testSetFile):
#             Window.File_Open(self, self.testSetFile,self.testSetSheet,None)
#             saved_file = self.testSetFile
#             already_saved = False
#         else:
#             saved_file = False
#             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#             box.place(x=333, y=66)
#             box.update()
#         global high, img, Maintain_test_set
#         if high:
#             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
#             high.image = img
#             Window.x1.forget()
#             Window.x2.forget()
#             Maintain_test_set = False
#         high = b
#         self.color = color
#         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image44.png"
#         photo2 = ImageTk.PhotoImage(Image.open(img))
#         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
#         b.image = photo2
#         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image4.png"))
#         if self.isOpenMTS:
#             self.texts2Set = {"Delete Row": self.testSetObject.deleteRow, "Add Row": self.testSetObject.addRow,
#                               "Add WorkBook": "",
#                               "Save As": lambda type='Master_', sheet=self.testSetSheet: self.testSetObject.saveAs(
#                                   type=type, sheetName=sheet),
#                               "Save": lambda file=self.testSetFile, sheet=self.testSetSheet: self.testSetObject.save(
#                                   filename=file, sheetName=sheet),
#                               "Open": self.open_Test_Sets}
#         else:
#             self.texts2Set = {"Delete Row":self.save_no_sheet, "Add Row":self.save_no_sheet, "Add WorkBook":self.save_no_sheet, "Save As":self.save_no_sheet, "Save":self.save_no_sheet,
#                   "Open": self.open_Test_Sets}
#         self.new_layout(self.texts2Set)
#         if saved_file:
#             self.display_workbook(self.header_label, saved_file)
#     def Test_Management(self, b, color):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and saved_file is not self.testMngtFile and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         try:
#             if name:
#               name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#               name=''
#         except:
#             pass
#         if (self.testMngtFile):
#             self.testMngtdObject = Window.File_Open(self, self.testMngtFile, self.testMngtSheet,None)
#             saved_file = self.testMngtFile
#             already_saved = False
#         else:
#             saved_file = False
#             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#             box.place(x=333, y=66)
#             box.update()
#         global high, img, Test_management
#         if high:
#             high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
#             high.image = img
#             Window.x1.forget()
#             Window.x2.forget()
#             Test_management = False
#         high = b
#         self.color = color
#         img = os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image55.png"
#         photo2 = ImageTk.PhotoImage(Image.open(img))
#         b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
#         b.image = photo2
#         img = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image5.png"))
#         if self.isOpenTM:
#             self.texts2Mngt = {
#                 "Save": lambda file=self.testMngtFile, sheet=self.testMngtSheet: self.testMngtdObject.save(
#                     filename=file,
#                     sheetName=sheet),
#                 "Open": self.open_Test_Mngt}
#         else:
#             self.texts2Mngt = {"Save":self.save_no_sheet, "Open": self.open_Test_Mngt}
#         self.new_layout(self.texts2Mngt)
#         lab = Label(self.header_label, text=" Test Management Tool", bg=self.topbar_colour, fg="black")
#         lab.pack()
#         lab.place(x=360, y=2)
#         self.texts3Mngt = {"ALM": "", "JIRA": "", "NA": ""}
#         self.new_checkBox(self.texts3Mngt)
#         CheckVar = IntVar()
#         self.C = Checkbutton(self.header_label, text="Create Defects Automatically", variable=CheckVar, onvalue=1,
#                              offvalue=0,
#                              bg=self.topbar_colour, fg="black")
#         self.C.pack()
#         self.C.place(x=650, y=30)
#         if saved_file:
#             self.display_workbook(self.header_label, saved_file)
#     def createbox(self):
#         box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
#         box.place(x=333, y=66)
#         box.update()
#     def mouseClick1(self, event):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and saved_file is not self.testScriptSelectedFile and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         if (self.testScriptSelectedFile):
#             saved_file = self.testScriptSelectedFile
#             already_saved = False
#             self.open_Test_Scripts(None, self.testScriptSelectedFile, self.SheetFromScriptsCombo)
#         else:
#             box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
#             box.place(x=333, y=66)
#             box.update()
#             self.texts2script = {"Delete Row": self.save_no_sheet, "Add Row": self.save_no_sheet,
#                                  "Add WorkBook": self.save_no_sheet, "Save As": self.save_no_sheet,
#                                  "Save": self.save_no_sheet,
#                                  "Open": self.open_Test_Scripts}
#             self.new_layout(self.texts2script)
#             self.texts1script = {"Select Script Sheet": ["Select Script Sheet"]}
#             size = [20]
#             pos = [360]
#             self.new_label(self.texts1script, size, pos, file=self.testScriptSelectedFile,
#                            obj=self.testScriptSelectedObject)
#         if name:
#             name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#         name = self.sub1
#         texts = self.sub1['text'].strip("-    ")
#         self.sub1.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + texts, anchor='w',
#                             bg="white")
#         if saved_file:
#             self.display_workbook(self.header_label, saved_file)
#     def mouseClick2(self, event):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and saved_file is not self.resuableComponentFile and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         if (self.resuableComponentFile):
#             saved_file = self.resuableComponentFile
#             already_saved = False
#             self.open_Reusable_Components(None, self.resuableComponentFile, self.SheetFromComponentCombo)
#         else:
#             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#             box.place(x=333, y=66)
#             box.update()
#             self.texts2Resuable = {"Delete Row": self.save_no_sheet, "Add Row": self.save_no_sheet,
#                                    "Add WorkBook": self.save_no_sheet, "Save As": self.save_no_sheet,
#                                    "Save": self.save_no_sheet, "Open": self.open_Reusable_Components}
#             self.new_layout(self.texts2Resuable)
#             self.texts1Resuable = {"Select the Component Sheet":["Select the Component Sheet"]}
#             size = [25]
#             pos = [360]
#             self.new_label(self.texts1Resuable, size, pos, file=self.resuableComponentFile,
#                            obj=self.resuableComponentObject)
#         if name:
#             name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#         name = self.sub2
#         texts = self.sub2['text'].strip("-    ")
#         self.sub2.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + texts, anchor='w',
#                             bg="white")
#
#         if saved_file:
#             self.display_workbook(self.header_label, saved_file)
#
#     def mouseClick3(self, event):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and saved_file is not self.objectFile and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         if (self.objectFile):
#             saved_file = self.objectFile
#             already_saved = False
#             self.open_Object_Repository(None,self.objectFile,self.SheetFromObjectCombo)
#
#         else:
#             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#             box.place(x=333, y=66)
#             box.update()
#             self.texts2Object = {"Add Column": self.save_no_sheet, "Delete Row": self.save_no_sheet,
#                                  "Add Row": self.save_no_sheet, "Add WorkBook": self.save_no_sheet,
#                                  "Save As": self.save_no_sheet,
#                                  "Save": self.save_no_sheet, "Open": self.open_Object_Repository}
#             self.new_layout(self.texts2Object)
#             self.texts1Object = {"Select the Objects Excel": ["Select the Objects Excel"]}
#             size = [23]
#             pos = [360]
#             self.new_label(self.texts1Object, size, pos,file=self.resuableComponentFile,obj=self.resuableComponentObject)
#
#         if name:
#             name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#         name = self.sub3
#         texts = self.sub3['text'].strip("-    ")
#         self.sub3.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + texts, anchor='w',
#                             bg="white")
#
#         if saved_file:
#             self.display_workbook(self.header_label, saved_file)
#
#
#     def mouseClick4(self, event):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and saved_file is not self.testDataFile and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         if (self.testDataFile):
#
#             saved_file = self.testDataFile
#             already_saved = False
#             self.open_Test_Data(None, self.testDataFile, self.testDataObject)
#         else:
#             box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#             box.place(x=333, y=66)
#             box.update()
#             self.texts2Data = {"Delete Column": self.save_no_sheet, "Add Column": self.save_no_sheet,
#                                "Delete Row": self.save_no_sheet, "Add Row": self.save_no_sheet,
#                                "Add WorkBook": self.save_no_sheet,
#                                "Save As": self.save_no_sheet, "Save": self.save_no_sheet, "Open": self.open_Test_Data}
#             self.new_layout(self.texts2Data)
#             self.texts1Data = {"Select the Test Data Sheet": ["Select the Test Data Sheet"]}
#             size = [23]
#             pos = [360]
#             self.new_label(self.texts1Data, size, pos, file=self.testDataFile, obj=self.testDataObject)
#
#         if name:
#             name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#         name = self.sub4
#         texts = self.sub4['text'].strip("-    ")
#         root.update()
#         self.sub4.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + texts, anchor='w',
#                             bg="white")
#
#
#
#         if saved_file:
#             self.display_workbook(self.header_label, saved_file)
#     def mouseClick5(self, event):
#
#         global saved_file, already_saved, name, texts, save_object
#         try:
#             if saved_file and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         saved_file = ""
#         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#         box.place(x=333, y=66)
#         box.update()
#         if name:
#             name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#         name = self.sub5
#         texts = self.sub5['text'].strip("-    ")
#         self.sub5.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + texts, anchor='w',
#                             bg="white")
#         root.update()
#         if self.isOpenET:
#             self.texts2Exec = {"Execute Test": hqdriver.hqDriver.execute, "Delete Row": self.execObject.deleteRow,
#                                "Add Row": self.execObject.addRow,
#                                "Save": lambda file=self.execFile, sheet=self.execSheet: self.execObject.save(
#                                    filename=file, sheetName=sheet), "Open Driver": self.open_Test_Execution}
#         else:
#             self.texts2Exec = {"Execute Test":hqdriver.hqDriver.execute, "Delete Row": self.save_no_sheet, "Add Row":self.save_no_sheet, "Save":self.save_no_sheet,"Open Driver":self.open_Test_Execution }
#         self.new_layout(self.texts2Exec)
#
#     def mouseClick6(self, event):
#         global saved_file, already_saved,name,texts,save_object
#         try:
#             if saved_file and not already_saved:
#                 self.save_file(save_object,saved_file)
#         except:
#             pass
#         saved_file = ""
#         box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width(), bg="white", padx=3, pady=3)
#         box.place(x=333, y=66)
#         box.update()
#         if name:
#             name.configure(fg="#1163F7", text=texts, anchor='w', font=('IBMPlexSans', 9))
#         name = self.sub6
#         texts = self.sub6['text'].strip("-    ")
#         root.update()
#         self.sub6.configure(fg="#1163F7", font=('IBMPlexSans', 9, 'bold'), text="-    " + texts, anchor='w',
#                             bg="white")
#         texts2 = {}
#         self.new_layout(texts2)
#         root1 = os.path.dirname(dir_path) + '\\test results'
#         dirlist = [item for item in os.listdir(root1) if os.path.isdir(os.path.join(root1, item))]
#         texts1 = {" Report Format": dirlist}
#         self.size = [25]
#         self.pos = [360]
#         for i in range(len(texts1)):
#          self.var = tk.StringVar()
#          self.comboExample = ttk.Combobox(textvariable=self.var, state="readonly",
#                                          values=[i for i in texts1[list(texts1)[i]]], width=self.size[i])
#          self.comboExample.set(list(texts1)[i])
#          self.comboExample.pack()
#          self.comboExample.bind("<<ComboboxSelected>>",
#                                lambda event, file1=None, obj1=None: self.comboBoxAction6(file1, obj1, event))
#          self.comboExample.place(x=self.pos[i], y=20)
#
#     def client_exit(self):
#         on_closing()
#
#     def open_Test_Scripts(self,sheetname=None,testScriptSelectedFile=None,thissheet=None):
#
#         global saved_file, already_saved,save_object
#         size = [20]
#         pos = [360]
#         if testScriptSelectedFile:
#             self.testScriptSelectedFile = testScriptSelectedFile
#             sheet = thissheet
#             self.SheetFromScriptsCombo = thissheet
#
#         if not sheetname and not testScriptSelectedFile:
#           self.testScriptSelectedFile = askopenfilename(
#             initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
#             filetypes=[("Excel files", "Script_*.xlsx .xls")])
#         if self.testScriptSelectedFile:
#             self.isOpenTS = True
#             wb = load_workbook(self.testScriptSelectedFile)
#             sheetNames = wb.sheetnames
#
#             self.texts1script = {sheetNames[0]: sheetNames}
#
#             if sheetname:
#
#                 sheet = sheetname
#                 self.SheetFromScriptsCombo = sheetname
#
#             else:
#                 self.SheetFromScriptsCombo = sheetNames[0]
#                 sheet = sheetNames[0]
#
#             saved_file = self.testScriptSelectedFile
#             already_saved = False
#
#             self.testScriptSelectedObject = Window.File_Open(self, self.testScriptSelectedFile, sheet,None)
#             save_object = self.testScriptSelectedObject
#             self.texts2script = {"Delete Row":self.testScriptSelectedObject.deleteRow, "Add Row":self.testScriptSelectedObject.addRow, "Add WorkBook": "", "Save As":lambda type='Script_',sheet=self.SheetFromScriptsCombo:self.testScriptSelectedObject.saveAs(type=type,sheetName=sheet),
#                                  "Save": lambda file=self.testScriptSelectedFile,
#                                                 sheet=self.SheetFromScriptsCombo: self.testScriptSelectedObject.save(
#                                      filename=file, sheetName=sheet),
#                                  "Open": self.open_Test_Scripts}
#             self.new_layout(self.texts2script)
#             self.new_label(self.texts1script, size, pos, file=self.testScriptSelectedFile,
#                            obj=self.testScriptSelectedObject,sheet=sheet)
#             self.display_workbook(self.header_label, saved_file)
#         else:
#             pass
#
#     def open_Reusable_Components(self,sheetname=None,resuableComponentFile=None,thissheet=None):
#
#         global saved_file, already_saved,save_object
#         size = [25]
#         pos = [360]
#         if resuableComponentFile:
#             self.resuableComponentFile = resuableComponentFile
#             sheet = thissheet
#             self.SheetFromComponentCombo = thissheet
#
#         if not sheetname and not resuableComponentFile:
#           self.resuableComponentFile = askopenfilename(
#             initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
#             filetypes=[("Excel files", "Reusable_Components*.xlsx .xls")])
#         if self.resuableComponentFile:
#             self.isOpenRC = True
#             wb = load_workbook(self.resuableComponentFile)
#             sheetNames = wb.sheetnames
#             self.texts1Resuable = {sheetNames[0]: sheetNames}
#
#             if sheetname:
#                 sheet = sheetname
#                 self.SheetFromComponentCombo = sheetname
#
#
#             else:
#                 self.SheetFromComponentCombo = sheetNames[0]
#                 sheet = self.resuableComponentSheet
#
#             saved_file = self.resuableComponentFile
#             already_saved = False
#             self.resuableComponentObject = Window.File_Open(self, self.resuableComponentFile, sheet,None)
#             save_object = self.resuableComponentObject
#             self.texts2Resuable = {"Delete Row":self.resuableComponentObject.deleteRow, "Add Row":self.resuableComponentObject.addRow, "Add WorkBook": "", "Save As":lambda type='Reusable_',sheet=self.SheetFromComponentCombo:self.resuableComponentObject.saveAs(type=type,sheetName=sheet),
#                                    "Save": lambda file=self.resuableComponentFile,
#                                                   sheet=self.SheetFromComponentCombo: self.resuableComponentObject.save(
#                                        filename=file, sheetName=sheet), "Open": self.open_Reusable_Components}
#             self.new_layout(self.texts2Resuable)
#             self.new_label(self.texts1Resuable, size, pos, file=self.resuableComponentFile,
#                            obj=self.resuableComponentObject,sheet=sheet)
#             self.display_workbook(self.header_label, saved_file)
#         else:
#             pass
#
#     def open_Object_Repository(self,sheetname=None,objectFile=None,thissheet=None):
#
#          global saved_file, already_saved,save_object
#          size = [23]
#          pos = [360]
#          if objectFile:
#              self.objectFile=objectFile
#              sheet = thissheet
#              self.SheetFromObjectCombo = thissheet
#
#          if not sheetname and not objectFile:
#             self.objectFile = askopenfilename(
#              initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
#              filetypes=[("Excel files", "Script_*.xlsx .xls")])
#
#          if self.objectFile:
#
#              self.isOpenOR = True
#              wb = load_workbook(self.objectFile)
#              sheetNames = wb.sheetnames
#              self.texts1Object = {sheetNames[0]: sheetNames}
#
#              if sheetname:
#                  sheet=sheetname
#                  self.SheetFromObjectCombo=sheetname
#
#              else:
#                  self.SheetFromObjectCombo = sheetNames[0]
#                  sheet = self.objectSheet
#
#              saved_file = self.objectFile
#              already_saved = False
#
#
#          # self.display_workbook(saved_file)
#
#              self.objectObject = Window.File_Open(self, self.objectFile,sheet,None)
#              save_object = self.objectObject
#              self.texts2Object = {"Add Column":self.objectObject.addColumn, "Delete Row":self.objectObject.deleteRow, "Add Row":self.objectObject.addRow, "Add WorkBook": "", "Save As":lambda type='Script_',sheet=self.SheetFromObjectCombo:self.objectObject.saveAs(type=type,sheetName=sheet),
#                               "Save": lambda file=self.objectFile,
#                                              sheet=self.SheetFromObjectCombo: self.objectObject.save(filename=file,
#                                                                                                      sheetName=sheet),
#                               "Open": self.open_Object_Repository}
#              self.new_layout(self.texts2Object)
#
#              self.new_label(self.texts1Object,size, pos, file=self.objectFile,
#                         obj=self.objectObject,sheet=sheet)
#              self.display_workbook(self.header_label, saved_file)
#          else:
#             pass
#
#     def open_Test_Data(self,sheetname=None,testDataFile=None,thissheet=None):
#
#         global saved_file, already_saved,save_object
#         size = [23]
#         pos = [360]
#         if testDataFile:
#             self.testDataFile = testDataFile
#             sheet = thissheet
#             self.SheetFromDataCombo = thissheet
#
#         if not sheetname and not testDataFile:
#           self.testDataFile = askopenfilename(
#             initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
#             filetypes=[("Excel files", "TestData_*.xlsx .xls")])
#         if self.testDataFile:
#             self.isOpenTD = True
#
#             wb = load_workbook(self.testDataFile)
#             sheetNames = wb.sheetnames
#             self.texts1Data = {sheetNames[0]: sheetNames}
#
#             if sheetname:
#                 sheet = sheetname
#                 self.SheetFromDataCombo = sheetname
#
#             else:
#                 self.SheetFromDataCombo = sheetNames[0]
#                 sheet = sheetNames[0]
#
#             saved_file = self.testDataFile
#             already_saved = False
#             self.testDataObject = Window.File_Open(self, self.testDataFile, sheet,None)
#             save_object = self.testDataObject
#             self.texts2Data = {"Delete Column": self.testDataObject.deleteColumn, "Add Column":self.testDataObject.addColumn, "Delete Row": self.testDataObject.deleteRow, "Add Row":self.testDataObject.addRow,
#                                "Add WorkBook": "",
#                                "Save As":lambda type='TestData_',sheet=self.SheetFromDataCombo:self.testDataObject.saveAs(type=type,sheetName=sheet),
#                                "Save": lambda file=self.testDataFile,
#                                               sheet=self.SheetFromDataCombo: self.testDataObject.save(
#                                    filename=file, sheetName=sheet), "Open": self.open_Test_Data}
#             self.new_layout(self.texts2Data)
#             self.new_label(self.texts1Data, size, pos, file=self.testDataFile,
#                            obj=self.testDataObject,sheet=sheet)
#             self.display_workbook(self.header_label, saved_file)
#         else:
#             pass
#
#     def open_Test_Sets(self):
#
#         global saved_file, already_saved, display,save_object
#         self.testSetFile = askopenfilename(initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
#                                            filetypes=[("Excel files", "Master_*.xlsx .xls")])
#         if self.testSetFile:
#             self.isOpenMTS = True
#             saved_file = self.testSetFile
#             already_saved = False
#             self.testSetObject = Window.File_Open(self, self.testSetFile, self.testSetSheet,None)
#             save_object = self.testSetObject
#             self.texts2Set = {"Delete Row": self.testSetObject.deleteRow, "Add Row":self.testSetObject.addRow, "Add WorkBook": "", "Save As":lambda type='Master_',sheet=self.testSetSheet:self.testSetObject.saveAs(type=type,sheetName=sheet),
#                               "Save": lambda file=self.testSetFile, sheet=self.testSetSheet: self.testSetObject.save(
#                                   filename=file, sheetName=sheet),
#                               "Open": self.open_Test_Sets}
#             self.new_layout(self.texts2Set)
#             self.display_workbook(self.header_label, saved_file)
#         else:
#             pass
#     def open_Generate_Scripts(self):
#
#         global saved_file, already_saved, display,save_object
#         self.generateTestFile = os.path.dirname(dir_path) + '/SAP/TestData//config.xlsx'
#         if self.generateTestFile:
#             self.isOpenGS = True
#             saved_file = self.generateTestFile
#             already_saved = False
#             self.generateTestObject = Window.File_Open(self, self.generateTestFile, self.generateSheet,None)
#             save_object = self.generateTestObject
#             self.texts2Generate = {"Generate Script": self.generate_Script_func, "Save":lambda file=self.generateTestFile,sheet=self.generateSheet:self.generateTestObject.save(filename=file,sheetName=sheet),
#                                    "Open": self.open_Generate_Scripts}
#
#             self.new_layout(self.texts2Generate)
#             self.display_workbook(self.header_label, saved_file)
#         else:
#             pass
#
#     def open_Test_Mngt(self):
#
#         global saved_file, already_saved, display,save_object
#         dir_path = os.path.dirname(os.path.realpath(__file__))
#         self.testMngtFile = os.path.dirname(dir_path) + '/SAP/TestData/Execution.xlsx'
#         if self.testMngtFile:
#             self.isOpenTM = True
#             saved_file = self.testMngtFile
#             already_saved = False
#             self.texts2Mngt = {
#                 "Save": lambda file=self.testMngtFile, sheet=self.testMngtSheet: self.testMngtdObject.save(filename=file,
#                                                                                               sheetName=sheet),
#                 "Open": self.open_Test_Mngt}
#             self.new_layout(self.texts2Mngt)
#             lab = Label(self.header_label, text=" Test Management Tool", bg=self.topbar_colour, fg="black")
#             lab.pack()
#             lab.place(x=360, y=2)
#             self.texts3Mngt = {"ALM": "", "JIRA": "", "NA": ""}
#             self.new_checkBox(self.texts3Mngt)
#             CheckVar = IntVar()
#             self.C = Checkbutton(self.header_label, text="Create Defects Automatically", variable=CheckVar, onvalue=1,
#                                  offvalue=0,
#                                  bg=self.topbar_colour, fg="black",command=partial(self.selected_checkBox,CheckVar))
#             self.C.pack()
#             self.C.place(x=650, y=30)
#             self.testMngtdObject = Window.File_Open(self, self.testMngtFile, self.testMngtSheet,None)
#             save_object = self.testMngtdObject
#             self.display_workbook(self.header_label, saved_file)
#         else:
#             pass
#
#     def open_Test_Execution(self):
#         self.isOpenET = True
#         global saved_file, already_saved, display,save_object
#         dir_path = os.path.dirname(os.path.realpath(__file__))
#         self.execFile = os.path.dirname(dir_path) + '/SAP/TestData/Execution.xlsx'
#         saved_file = self.execFile
#         already_saved = False
#         self.execObject = Window.File_Open(self, self.execFile,self.execSheet,None)
#         save_object=self.execObject
#         self.texts2Exec = {"Execute Test": hqdriver.hqDriver.execute, "Delete Row":self.execObject.deleteRow, "Add Row":self.execObject.addRow,
#                            "Save":lambda file=self.execFile,sheet=self.execSheet:self.execObject.save(filename=file,sheetName=sheet), "Open Driver": self.open_Test_Execution}
#         self.new_layout(self.texts2Exec)
#         self.display_workbook(self.header_label, saved_file)
#
#     def File_Open(self, file, sheet,box):
#         try:
#             if os.path.exists(file):
#                 if not box:
#                     box = Frame(self, height=root.winfo_height() - 66, width=root.winfo_width() - 345, bg="white",
#                                 padx=3,
#                                 pady=3)
#                     box.place(x=333, y=66)
#                     box.update()
#                 else:
#                     box = box
#                 a = MyTable
#                 pt = a.make_table(box, file, sheet, height=box.winfo_height() - 70, width=box.winfo_width() - 75)
#
#
#             return pt
#         except:
#             pass
#
#     def save_file(self,save_object,save_file):
#         global already_saved
#         MsgBox = tk.messagebox.askquestion('Save Excel', 'Save before leaving',
#                                            icon='warning')
#         if MsgBox == 'yes':
#
#             already_saved = True
#
#             save_object.save(filename=save_file)
#
#
#         else:
#             already_saved = False
#             tk.messagebox.CANCEL()
#
#     def new_layout(self, texts2):
#         self.header_label = tk.Frame(root, bg=self.topbar_colour, width=root.winfo_width(), height=10)
#         self.header_label.pack(pady=0)
#         self.header_label.place(x=0, y=0, height=66)
#         x = 0
#         for i in range(len(texts2)):
#             x += 150
#             bg = tk.Button(self.header_label, text=list(texts2)[i], borderwidth=0, highlightbackground="white",
#                            highlightthickness=0, anchor='center', bg="#005CFB", fg="white", width=13,
#                            command=texts2[list(texts2)[i]])
#             bg.place(x=root.winfo_width() - x, y=15)
#
#     def new_label(self, texts1 ,size, pos,file=None,obj=None,sheet=None):
#         for i in range(len(texts1)):
#             self.var= tk.StringVar()
#
#
#             self.comboExample = ttk.Combobox(textvariable=self.var, state="readonly",
#                                              values=[i for i in texts1[list(texts1)[i]]], width=size[i])
#             if not sheet:
#              self.comboExample.set(list(texts1)[i])
#             else:
#                 self.comboExample.set(sheet)
#             self.comboExample.pack()
#             self.comboExample.bind("<<ComboboxSelected>>", lambda event,file1=file,obj1=obj :self.comboBoxAction(file1,obj1,event))
#             self.comboExample.place(x=pos[i], y=20)
#
#
#     def comboBoxAction(self,file,obj,event=None):
#         if event:
#
#             if obj == self.testDataObject:
#                 self.SheetFromDataCombo=self.var.get()
#                 self.open_Test_Data(self.SheetFromDataCombo)
#
#             elif obj==self.objectObject:
#                 self.SheetFromObjectCombo=self.var.get()
#                 self.open_Object_Repository(self.SheetFromObjectCombo)
#
#             elif obj==self.resuableComponentObject:
#                 self.SheetFromComponentCombo=self.var.get()
#                 self.open_Reusable_Components(self.SheetFromComponentCombo)
#
#
#             elif obj==self.testScriptSelectedObject:
#                 self.SheetFromScriptsCombo=self.var.get()
#                 self.open_Test_Scripts(self.SheetFromScriptsCombo)
#
#     def comboBoxAction6(self,file,obj,event=None):
#         global show
#         if event:
#
#             show= self.var.get()
#             box1 = Frame(self, height=root.winfo_height() - 116, width=(root.winfo_width() - 335) / 2, bg="white",
#                          padx=3,
#                          pady=3, highlightthickness=1, highlightbackground='black')
#             box1.place(x=333, y=116)
#             box1.update()
#             root1 = os.path.dirname(dir_path) + '\\test results'
#             open_this = root1 + '\\' + show +'\\TestResult.xlsx'
#             add = int(root.winfo_width() - 330) / 2
#             box2 = Frame(self, height=root.winfo_height() - 116, width=(root.winfo_width() - 335) / 2, bg="white", padx=3,
#                      pady=3, highlightthickness=1, highlightbackground='black')
#             box2.place(x=333 + add, y=116)
#             box2.update()
#             box11 = Frame(self, height=50, width=box1.winfo_width(), bg="white", padx=3,
#                       pady=3, highlightthickness=1, highlightbackground='black')
#             box11.place(x=333, y=66)
#             box22 = Frame(self, height=50, width=box2.winfo_width(), bg="white", padx=3,
#                       pady=3, highlightthickness=1, highlightbackground='black')
#             box22.place(x=334 + add, y=66)
#             lable1 = Label(box11, text="Summary Report", fg='black', bg='white', font=("IBMPlexSans", 12))
#             lable1.place(x=box1.winfo_width()/3, y=10)
#             lable2 = Label(box22, text="Detailed Summary", fg='black', bg='white', font=("IBMPlexSans", 12))
#             lable2.place(x=box2.winfo_width()/3, y=10)
#             Window.File_Open(self, open_this, 'summary report', box1)
#             Window.File_Open(self, open_this, 'summary', box2)
#
#     def selected_checkBox(self,CheckVar1):
#         if CheckVar1.get():
#             write(self.testMngtFile, self.testMngtSheet, 8, 2, "TRUE")
#             self.testMngtdObject.model.setValueAt("TRUE", 6, 1)
#             self.testMngtdObject.drawText(6, 1, "TRUE")
#         else:
#             write(self.testMngtFile, self.testMngtSheet, 8, 2, "FALSE")
#             self.testMngtdObject.model.setValueAt("FALSE", 6, 1)
#             self.testMngtdObject.drawText(6, 1, "FALSE")
#
#     def selected_radio(self, Checkvar1):
#         if Checkvar1.get()=="JIRA":
#             write(self.testMngtFile, self.testMngtSheet, 9, 2, "JIRA")
#             self.testMngtdObject.model.setValueAt("JIRA",7,1)
#             self.testMngtdObject.drawText(7,1,"JIRA")
#         elif Checkvar1.get()=="ALM":
#             write(self.testMngtFile,self.testMngtSheet, 9, 2, "ALM")
#             self.testMngtdObject.model.setValueAt("ALM", 7, 1)
#             self.testMngtdObject.drawText(7, 1, "ALM")
#         else:
#             write(self.testMngtFile, self.testMngtSheet, 9, 2, "NA")
#             self.testMngtdObject.model.setValueAt("NA", 7, 1)
#             self.testMngtdObject.drawText(7 , 1, "NA")
#
#     def new_checkBox(self, texts3):
#         x = 360
#         CheckVar1 = StringVar()
#         for i in range(len(texts3)):
#             self.C = Radiobutton(self.header_label, text=list(texts3)[i], var=CheckVar1, value=list(texts3)[i],
#                                  bg=self.topbar_colour, fg="black", tristatevalue=0,
#                                  command=partial(self.selected_radio, CheckVar1))
#             self.C.pack()
#             self.C.place(x=x, y=30)
#             x = x + 66
#
#     def save_no_sheet(self):
#         messagebox.showinfo("Error", "Please Select the  Sheet")
#
# class MyTable(Table):
#     def __init__(self, parent=None, **kwargs):
#         Table.__init__(self, parent, **kwargs)
#         return
#
#     def saveAs(self,type,sheetName=None):
#         """Save dataframe to file"""
#         filename = asksaveasfilename(initialdir=os.path.dirname(dir_path) + '/SAP/TestData',
#                                          filetypes=[("Excel files", type+"*.xlsx .xls")], defaultextension=".xlsx",
#                                          initialfile=type)
#         if filename:
#             self.model.save(filename)
#             self.filename = filename
#             self.currentdir = os.path.basename(filename)
#         return
#
#     def save(self, filename, sheetName=None):
#
#         """Save current file"""
#
#         if filename:
#             self.model.save(filename, sheetName)
#             self.filename = filename
#             self.currentdir = os.path.basename(filename)
#         # else:
#         #     messagebox.showinfo("Error", "Please Select the  Sheet")
#         return
#
#     def make_table(frame, file, sheet, **kwds):
#         """make a sample table"""
#         wb = load_workbook(file)
#         sheetNames = wb.sheetnames
#         excel = file
#         if sheet:
#
#             if sheet not in sheetNames:
#                 df = pd.read_excel(excel, skipinitialspace=True, encoding='utf-8')
#             else:
#                 df = pd.read_excel(excel, sheet_name=sheet, skipinitialspace=True, encoding='utf-8')
#         else:
#             df = pd.read_excel(excel, skipinitialspace=True, encoding='utf-8')
#         pt = MyTable(frame, dataframe=df, **kwds)
#         pt.editable = True
#
#         pt.show()
#         return pt
#
# class ToggledFrame(tk.Frame):
#     def __init__(self, parent, bg, image, text="", *args, **options):
#         tk.Frame.__init__(self, parent, *args, **options)
#         self.title_frame = ttk.Frame(self)
#         self.title_frame.pack()
#         self.color = bg
#         self.toggle_button = tk.Button(self.title_frame, image=image, compound="left", text=" " + text, anchor='w',
#                                        bg=self.color, fg="white", height=20,
#                                        highlightbackground="black", borderwidth=0, padx=15, font=("IBMPlexSans", 9))
#         self.toggle_button.config(
#             command=partial(lambda *args: self.toggle(self.toggle_button, self.color, image), self.toggle_button))
#         self.toggle_button.grid(row=3, column=0, ipady=20, sticky='nesw', ipadx=66)
#         self.toggle_button.image = image
#         self.sub_frame = tk.Frame(self, relief="sunken")
#
#     def toggle(self, button, color, image):
#         global high, x, img, Manage_test_script
#         if high and high is not button:
#             high.config(fg="white", image=img, font=("IBMPlexSans", 9), bg=self.color)
#             high.image = img
#             self.sub_frame.forget()
#         high = button
#         self.color = color
#         img = image
#         txt = button.config('text')[-1][:-1]
#         if button.cget('bg') in ["#1163F7"]:
#             if txt.strip() == "Manage Test Scripts":
#                 img1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image33.png"))
#             else:
#                 img1 = ImageTk.PhotoImage(Image.open(os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\image66.png"))
#             button.config(fg="#1163F7", image=img1, compound='left', font=('IBMPlexSans', 9, 'bold'), bg="white")
#             button.image = img1
#             self.sub_frame.pack()
#             root.update()
#             self.header_label = tk.Frame(root, bg='#d5d5d5', width=root.winfo_width(), height=10)
#             self.header_label.pack(pady=0)
#             self.header_label.place(x=0, y=0, height=66)
#         else:
#             button.config(fg="white", image=img, compound='left', font=('IBMPlexSans', 9), bg=self.color)
#             button.image = img
#             self.sub_frame.forget()
#             root.update()
#             self.header_label = tk.Frame(root, bg="#d5d5d5", width=root.winfo_width(), height=10)
#             self.header_label.pack(pady=0)
#             self.header_label.place(x=0, y=0, height=66)
#
#
# def on_closing():
#     if messagebox.askokcancel("Quit","Do you want to exit?"):
#         port_killer()
#         root.destroy()
#
# def center(e):
#     w = int(root.winfo_width() / 5)  # get root width and scale it ( in pixels )
#     s = 'IBM UI4AutomationFrameworks'.rjust(w // 2)
#     root.title(s)
#
#
# root = Tk()
# c=os.system("taskkill /f /im  excel.exe")
# print(c)
# c=os.system("taskkill /f /im  winword.exe")
#
# root.bind("<Configure>", center)
# root.state('zoomed')
# root.iconbitmap(default=os.path.dirname(dir_path)+"\\UIAF_Icons_Data\\Icon1.ico")
# ExecutionFile = os.path.dirname(dir_path) + '/SAP/TestData/Execution.xlsx'
# write(ExecutionFile, "defecttoolinfo", 9, 2, "NA")
# write(ExecutionFile, "defecttoolinfo", 8, 2, "FALSE")
# high, img,name,texts,save_object = "", "","","",""
# x = False
# dir_path = os.path.dirname(os.path.realpath(__file__))
# Record, Generate, Manage_test_script, Maintain_test_set, Test_management, Test_execution = True, True, True, True, True, True
# app = Window(root)
# saved_file, already_saved = "", False
# root.protocol("WM_DELETE_WINDOW",on_closing)
# root.mainloop()
