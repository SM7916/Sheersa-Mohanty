# Simple enough, just import everything from tkinter.
from tkinter import *
from tkinter import ttk
import tkinter as tk
from PIL import ImageTk, Image
from functools import partial
from pandastable.core import Table
from pandastable.data import TableModel
import pandas as pd
from docx import Document

import docx2txt
from tkinter.filedialog import askopenfilename,asksaveasfile
from ctypes import windll, pointer, wintypes
windll.shcore.SetProcessDpiAwareness(1)


# Here, we are creating our class, Window, and inheriting from the Frame
# class. Frame is a class from the tkinter module. (see Lib/tkinter/__init__)
class Window(Frame):

    # Define settings upon initialization. Here you can specify
    def __init__(self, master=None):

        Frame.__init__(self, master)
        self.topbar_colour="#d5d5d5"


        self.master = master


        self.init_window()


    def init_window(self):
        self.configure(bg="white")

        self.pack(fill=BOTH, expand=1)
        box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
        box.place(x=333, y=66)
        box.update()
        file = open("C:\\Users\\LIJAMANNATHARA\\Downloads\\a.txt",'r')
        text=file.read()

        Window.labb = Label(box, text=text, anchor="w",  justify='left', bg="white", padx=30,
                            pady=30)

        # labb.grid(row=0,column=0,ipadx=10,ipady=200)
        # labb.grid_rowconfigure(0,weight=1)
        # labb.grid_columnconfigure(0, weight=1)

        Window.labb.place(x=0, y=0)
        Window.labb.pack()

        menu = Menu(self.master)
        menu = Menu(self.master)

        self.master.config(menu=menu)
        menu.config(title="bharath")


        file = Menu(menu,tearoff=0)
        space=Menu(menu)
        space.add_separator()
        file.add_command(label="Exit", command=self.client_exit)
        menu.add_cascade(label="File", menu=file)
        Configure = Menu(menu,tearoff=0)
        Configure.add_command(label="Undo")
        menu.add_cascade(label="Configure", menu=Configure)
        About = Menu(menu,tearoff=0)

        menu.add_command(label="About",command=self.About)
        root.update()
        self.update()
        print(root.winfo_height())
        label =Frame(self, bg="#1163F7",highlightbackground="#1163F7", borderwidth=0, relief="groove",width=333, height=root.winfo_height())
        if root.update():
            label = Frame(self, bg="#1163F7", highlightbackground="#1163F7", borderwidth=0, relief="groove", width=333,
                          height=root.winfo_height())

        label.update()
        label.grid(row=0, column=0)

        label.grid_columnconfigure(0, weight=1)

        label.grid_propagate(False)

        frame = tk.Frame(label, background='white', borderwidth=0, relief='sunken')
        frame.grid(row=0, column=0, sticky='nesw', ipadx=60)
        frame.grid_columnconfigure(0, weight=1)
        self.b = tk.Button(frame, text="", state='disabled', bg="#d5d5d5")

        self.b.grid(row=0, column=0, sticky='nesw', ipady=10)

        frame = Label(self, fg="white",text="@Copyright IBM Corp. 2020 All Rights Reserved.",background='#1163F7', borderwidth=0, relief='sunken',width=40,anchor="w",font=('IBMPlexSans',7),padx=6)


        frame.place(x=0, y=self.winfo_height()-30)
        frame = Label(self, fg="white", text="@Copyright IBM Corp. 2020 All Rights Reserved.", background='#1163F7',
                      borderwidth=0, relief='sunken', width=40, anchor="w", font=('IBMPlexSans', 7), padx=6)

        frame.place(x=0, y=self.winfo_height() - 30)





        frame = Frame(label, background='white', borderwidth=0.5, relief='sunken')
        frame.grid(row=3, column=0, sticky='nesw', ipadx=66)
        frame.grid_columnconfigure(0, weight=1)

        photo1 = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image3.png"))


        t = ToggledFrame(frame,image=photo1,text='  Manage Test Scripts ', relief="raised", bg="#1163F7")
        t.grid(row=3, column=0, sticky='nesw')
        Window.x1=t.sub_frame


        self.sub1 = tk.Label(t.sub_frame, text="Test Scripts",  bg="white", fg="#1163F7",anchor='w',padx=60,font=('IBMPlexSans',9))
        self.sub1.bind('<Button>', self.mouseClick1)
        self.sub1.grid(ipadx=66, ipady=10, sticky='nesw')


        self.sub2 = tk.Label(t.sub_frame, text="Reusable Components", bg="white", fg="#1163F7",anchor='w',padx=60,font=('IBMPlexSans',9))
        self.sub2.bind("<Button>", self.mouseClick2)
        self.sub2.grid(ipadx=66, ipady=10, sticky='nesw')

        self.sub3 = tk.Label(t.sub_frame, text="Object Repository", bg="white", fg="#1163F7", anchor='w',padx=60,font=('IBMPlexSans',9))
        self.sub3.bind("<Button>", self.mouseClick3)
        self.sub3.grid(ipadx=66, ipady=10, sticky='nesw')

        self.sub4 = tk.Label(t.sub_frame, text="Test Data", bg="white", fg="#1163F7", anchor='w',padx=60,font=('IBMPlexSans',9))
        self.sub4.bind("<Button>", self.mouseClick4)
        self.sub4.grid(ipadx=66, ipady=10, sticky='nesw')
####################################################################################################3
        #row 7
        frame = Frame(label, background='white', borderwidth=0.5, relief='sunken')
        frame.grid(row=7, column=0, sticky='nesw', ipadx=66)
        frame.grid_columnconfigure(0, weight=1)

        photo1 = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image6.png"))
        t = ToggledFrame(frame, image=photo1,text=' Test Execution/Reporting ', relief="raised", bg="#1163F7")
        # self.t.config(bg='white')
        t.grid(row=6, column=0, sticky='nesw')
        Window.x2=t.sub_frame
###########################################################################################################


        self.sub5 = tk.Label(t.sub_frame, text="Execute Tests", bg="white", fg="#1163F7", anchor='w',padx=60,font=('IBMPlexSans',9))
        self.sub5.bind("<Button>", self.mouseClick5)
        self.sub5.grid(ipadx=100, ipady=10, sticky='nesw')

        self.sub6 = tk.Label(t.sub_frame, text="View Test Report", bg="white", fg="#1163F7", anchor='w',padx=60,font=('IBMPlexSans',9))
        self.sub6.bind("<Button>", self.mouseClick6)
        self.sub6.grid(ipadx=100, ipady=10, sticky='nesw')
    ###############################################


#############################################################################################################33
        # b.place(x=0, y=0, height=55, width=170)
        button_name = {"":"", "Record Flows":self.Record_Flows, "Generate Tests":self.Generate_Tests, "Manage Test Scripts":"", "Maintain Test Sets":self.Maintain_Test_Sets,
                       "Test Management":self.Test_Management}

        for i in range(1, 6):
            if i is 3 :
               continue

            self.color="#1163F7"

            frame = Frame(label, background='white', borderwidth=0, relief="flat")
            frame.grid(row=i, column=0, sticky='nesw', ipadx=66)
            frame.grid_columnconfigure(0, weight=1)
            img="C:\\Users\\LIJAMANNATHARA\\Downloads\\image"+str(i)+".png"
            photo1 = ImageTk.PhotoImage(Image.open(img))
            text="   "+list(button_name)[i]
            self.b = Button(frame,image=photo1,compound="left",text=text, anchor='w',highlightbackground="white",borderwidth=1,highlightthickness=2,relief="groove", bg=self.color, fg="white",height=20,padx=15,font=("IBMPlexSans",9))  # anchor for text justification
            self.b.config(command=partial(button_name[list(button_name)[i]],self.b,self.color))
            self.b.grid(row=i, column=0, sticky='nesw',ipady=20)
            self.b.image = photo1
            # b.config(highlightbackground="white")


            # b.config(image=img, width="2", height="2")
            # b.pack(side=LEFT)



            # b.place(x=0, y=45 * i, height=55, width=170)


        #################################################################################################

        texts2 = {}
        frame_work=self.new_layout(texts2)
        workbook_name=Label(frame_work,text="Master WorkBook Name",bg="#d5d5d5",fg="black",anchor="w",padx=20,pady=10)
        workbook_name.config(height=1,width=29)
        workbook_name.place(x=0,y=1)



        # self.bottom_frame = tk.Frame(root, bg="white",width=root.winfo_width(),height=30)
        # root.update()
        # self.bottom_border=root.winfo_height()-30
        #
        # self.bottom_frame.place(x=0, y=self.bottom_border, height=30)
        #
        # self.bottom_label = tk.Label(self.bottom_frame,text = "@Copyright IBM Corp. 2020 All Rights Reservers", bg="white",anchor='center')
        #
        # self.bottom_label.config(width=200)
        # self.bottom_label.pack()
        # scrollbar = Scrollbar(root)
        # scrollbar.pack(side=RIGHT, fill=Y)

###################################################################################################3




#########################################################################################


#####################################################################################################3
        self.name=None
        self.texts=None
        self.high=None

########################################################################################
    def About(self):
        Window.labb.forget()
        texts = {}
        self.new_layout(texts)
        box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
        box.place(x=333, y=66)
        box.update()
        with open("C:\\Users\\LIJAMANNATHARA\\Downloads\\About.txt", 'r') as f:
            text = f.read()
        Window.labb=Label(box,text=text,anchor="w",justify='left',bg="white",padx=30,pady=30)
        Window.labb.place(x=0,y=0)
        Window.labb.pack()
################################################################################################################

    def Record_Flows(self,b,color):
        Window.labb.forget()
        global high,img,Record
        if Record:
            box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
            box.place(x=333, y=66)
            box.update()
            file = open("C:\\Users\\LIJAMANNATHARA\\Downloads\\a.txt", 'r')
            text = file.read()
            Window.labb = Label(box, text=text, anchor="w", justify='left', bg="white", padx=30,pady=30)
            Window.labb.place(x=0, y=0)
            Window.labb.pack()
        texts = {}
        if high:
          high.config(fg="white",image=img,compound='left',font=("IBMPlexSans",9),bg=self.color)
          high.image=img
          Window.x1.forget()
          Window.x2.forget()
          Record=False
        high=b
        self.color=color
        self.new_layout(texts)
        img = "C:\\Users\\LIJAMANNATHARA\\Downloads\\rec.png"
        photo1 = ImageTk.PhotoImage(Image.open(img))
        bg = tk.Button(self.header_label,image=photo1,compound='left',text=" Record",anchor='center', bg="#005CFB", fg="white",width=125,height=30,borderwidth=0)
        img = "C:\\Users\\LIJAMANNATHARA\\Downloads\\image11.png"
        photo2 = ImageTk.PhotoImage(Image.open(img))
        b.config(image=photo2,compound='left',fg="#1163F7",font=("IBMPlexSans",9,"bold"),bg="white")
        bg.place(x=root.winfo_width() - 150, y=15)
        bg.image=photo1
        b.image=photo2
        img = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image1.png"))


    def Generate_Tests(self,b,color):

        Window.labb.forget()
        global high,img,Generate
        print(Generate)
        if Generate:
            box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
            box.place(x=333, y=66)
            box.update()
            #file = open("C:\\Users\\LIJAMANNATHARA\\Downloads\\b.txt", 'r')
            #text = file.read()

            Window.labb = Label(box, text=text, anchor="w",  justify='left', bg="white", padx=30,
                                pady=30)
            Window.labb.place(x=0, y=0)
            Window.labb.pack()

        if high:
            high.config(fg="white", image=img, compound='left', font=("IBMPlexSans", 9), bg=self.color)
            high.image = img
            Window.x1.forget()
            Window.x2.forget()
            Generate=False
        high = b
        self.color=color
        img = "C:\\Users\\LIJAMANNATHARA\\Downloads\\image22.png"
        photo2 = ImageTk.PhotoImage(Image.open(img))
        b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
        b.image=photo2
        img = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image2.png"))



        texts2 = {"Generate Script": "","Save": "", "Open": self.File_Open}
        self.new_layout(texts2)



    def Maintain_Test_Sets(self,b,color):
        Window.labb.forget()
        # if Window.labb:
        #     Window.labb.forget()


        global high,img,Maintain_test_set
        if Maintain_test_set:
            box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
            box.place(x=333, y=66)
            box.update()
            file = open("C:\\Users\\LIJAMANNATHARA\\Downloads\\c.txt", 'r')
            text = file.read()

            Window.labb = Label(box, text=text, anchor="w",  justify='left', bg="white", padx=30,
                                pady=30)
            Window.labb.place(x=0, y=0)
            Window.labb.pack()

        if high:
            high.config(fg="white",image=img,compound='left',font=("IBMPlexSans",9),bg=self.color)
            high.image = img
            Window.x1.forget()
            Window.x2.forget()
            Maintain_test_set=False
        high = b
        self.color=color
        img = "C:\\Users\\LIJAMANNATHARA\\Downloads\\image44.png"
        photo2 = ImageTk.PhotoImage(Image.open(img))
        b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
        b.image = photo2
        img = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image4.png"))

        texts2= {"Delete Row":"","Add Row":"","Add WorkBook":"","Save As":"","Save":"","Open":self.File_Open}
        self.new_layout(texts2 )

    def Test_Management(self,b,color):
        # if Window.labb:
        #     Window.labb.forget()
        Window.labb.forget()

        global high,img,Test_management
        if Test_management:
            box = Frame(self, height=root.winfo_height() - 66, width=1566, bg="white", padx=3, pady=3)
            box.place(x=333, y=66)
            box.update()
            file = open("C:\\Users\\LIJAMANNATHARA\\Downloads\\d.txt", 'r')
            text = file.read()

            Window.labb = Label(box, text=text, anchor="w", justify='left', bg="white", padx=30,
                                pady=30)
            Window.labb.place(x=0, y=0)
            Window.labb.pack()

        if high:
            high.config(fg="white",image=img,compound='left',font=("IBMPlexSans",9),bg=self.color)
            high.image = img
            Window.x1.forget()
            Window.x2.forget()
            Test_management=False

        high = b
        self.color=color
        img = "C:\\Users\\LIJAMANNATHARA\\Downloads\\image55.png"
        photo2 = ImageTk.PhotoImage(Image.open(img))
        b.config(image=photo2, compound='left', fg="#1163F7", font=("IBMPlexSans", 9, "bold"), bg="white")
        b.image = photo2
        img = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image5.png"))

        texts2 = {"Save": "", "Open": self.File_Open}
        self.new_layout(texts2 )

        lab=Label(self.header_label,text=" Test Management Tool",bg=self.topbar_colour,fg="black")
        lab.pack()
        lab.place(x=360,y=2)

        texts3={"ALM":"","JIRA":"","NA":""}
        self.new_checkBox(texts3)
        CheckVar=IntVar()
        self.C = Checkbutton(self.header_label, text="Create Defects Automatically", variable=CheckVar, onvalue=1, offvalue=0,
                             bg=self.topbar_colour,fg="black")
        self.C.pack()
        self.C.place(x=650, y=30)

#####################################################################################################################
    def createbox(self):

        box = Frame(self, height=root.winfo_height()-66, width=1566, bg="white", padx=3, pady=3)
        box.place(x=333, y=66)
        box.update()




    def mouseClick1(self,event):


        if self.name:
            self.name.configure(fg="#1163F7",text=self.texts, anchor='w',font=('IBMPlexSans',9))
        self.name=self.sub1
        self.texts=self.sub1['text'].strip("-    ")
        self.sub1.configure(fg="#1163F7",font=('IBMPlexSans',9,'bold'),text = "-    "+self.texts,anchor='w',bg="white")
        root.update()

        texts2= {"Delete Row":"","Add Row":"","Add WorkBook":"","Save As":"","Save":"","Open":self.File_Open}
        # x = 210
        # for i in range(len(texts)):
        #     x += 95
        #     bg = tk.Button(self.header_label, text=list(texts)[i], anchor='center', bg="#005CFB", fg="white", width=11)
        #     bg.place(x=root.winfo_width() - x, y=10)

        texts1={"Selenium":[1,2,3],"Web":[4,5],"Select Script Sheet":[6,9]}
        size=[11,9,20]
        pos=[360,520,660]
        self.new_layout(texts2)
        self.new_label(texts1,size,pos)
        # self.new_label(self,texts1,x)
        # self.sub2 = tk.Label(self.header_label, text="Selenium", bg="white", fg="black", anchor='center',width=11)
        # # self.sub2.bind("<Button>", self.mouseClick2)
        # self.sub2.place(x=10,y=10)
        # self.sub2 = tk.Label(self.header_label, text="Web", bg="white", fg="black", anchor='center', width=9)
        # # self.sub2.bind("<Button>", self.mouseClick2)
        # self.sub2.place(x=110, y=10)
        # self.sub2 = tk.Label(self.header_label, text="Select Script Sheet", bg="white", fg="black", anchor='center', width=15)
        # # self.sub2.bind("<Button>", self.mouseClick2)
        # self.sub2.place(x=200, y=10)

    def mouseClick2(self,event):
        if self.name:
            self.name.configure(fg="#1163F7",text=self.texts, anchor='w',font=('IBMPlexSans',9))
        self.name = self.sub2
        self.texts = self.sub2['text'].strip("-    ")
        self.sub2.configure(fg="#1163F7",font=('IBMPlexSans',9,'bold'),text="-    " + self.texts, anchor='w',bg="white")

        root.update()

        texts2 = {"Delete Row": "", "Add Row": "", "Add WorkBook": "", "Save As": "", "Save": "", "Open": self.File_Open}
        self.new_layout(texts2 )
        texts1={"Select the Component Sheet":[1,2,3]}
        size=[25]
        pos=[360]
        self.new_label(texts1,size,pos)

    def mouseClick3(self,event):
        if self.name:
            self.name.configure(fg="#1163F7",text=self.texts, anchor='w',font=('IBMPlexSans',9))
        self.name = self.sub3
        self.texts = self.sub3['text'].strip("-    ")
        self.sub3.configure(fg="#1163F7",font=('IBMPlexSans',9,'bold'),text="-    " + self.texts, anchor='w',bg="white")

        texts2 = {"Add Column":"","Delete Row": "", "Add Row": "", "Add WorkBook": "", "Save As": "", "Save": "", "Open": self.File_Open}
        self.new_layout(texts2 )
        texts1 = {"Select the Objects Exel": ['T1','T2','T3']}
        size=[23]
        pos = [360]
        self.new_label(texts1, size,pos)

    def mouseClick4(self,event):
        if self.name:
            self.name.configure(fg="#1163F7",text=self.texts, anchor='w',font=('IBMPlexSans',9))
        self.name = self.sub4
        self.texts = self.sub4['text'].strip("-    ")
        root.update()
        self.sub4.configure(fg="#1163F7",font=('IBMPlexSans',9,'bold'),text="-    " + self.texts, anchor='w',bg="white")

        texts2 = {"Delete Column":"", "Add Column": "", "Delete Row": "", "Add Row": "", "Add WorkBook": "", "Save As": "", "Save": "","Open": self.File_Open}
        self.new_layout(texts2 )
        texts1 = {"Select the Test Data Sheet": [1,2,3,4,5]}
        size=[23]
        pos = [360]
        self.new_label(texts1, size,pos)

    def mouseClick5(self,event):
        if self.name:
            self.name.configure(fg="#1163F7",text=self.texts, anchor='w',font=('IBMPlexSans',9))
        self.name = self.sub5
        self.texts = self.sub5['text'].strip("-    ")
        self.sub5.configure(fg="#1163F7",font=('IBMPlexSans',9,'bold'),text="-    " + self.texts, anchor='w',bg="white")
        root.update()

        texts2 = {"Execute Test": "", "Delete Row": "", "Add Row": "",  "Save": "", }
        self.new_layout(texts2)


    def mouseClick6(self, event):
        if self.name:
            self.name.configure(fg="#1163F7",text=self.texts, anchor='w',font=('IBMPlexSans',9))
        self.name = self.sub6
        self.texts = self.sub6['text'].strip("-    ")
        root.update()
        self.sub6.configure(fg="#1163F7",font=('IBMPlexSans',9,'bold'),text="-    " + self.texts, anchor='w',bg="white")
        texts2={}
        self.new_layout(texts2 )

        texts1 = {" Report Format": [1, 2, 3, 4, 5]}
        size = [15]
        pos = [360]
        self.new_label(texts1, size, pos)

###################################################################################################


    def client_exit(self):
        exit()

    def File_Open(self):

        self.file = askopenfilename(filetypes=[('Excel Files', '*.xlsx')])
        if self.file is not None:
            # print(file)
            box = Frame(self, height=root.winfo_height()-66, width=1566,bg="white",padx=3,pady=3)
            box.place(x=333, y=66)
            # box.pack(fill=BOTH, expand=1)
            box.update()
            print(root.winfo_height())
            print(root.winfo_width())
            a=MyTable
            pt = a.make_table(box,self.file, height=box.winfo_height() - 70, width=box.winfo_width() - 70)


    # def file_save(self):
    #         f = asksaveasfile( mode='w', defaultextension=".xlsx")
    #         if f is None:  # asksaveasfile return `None` if dialog closed with "cancel".
    #             return
    #         text2save = str(self.file.get(1.0, END))  # starts from `1.0`, not `0.0`
    #         f.write(text2save)
    #         f.close()  # `()` was missing.

        ####################################################################################################
    def new_layout(self,texts2):
        self.header_label = tk.Frame(root, bg=self.topbar_colour, width=root.winfo_width(), height=10)


        self.header_label.pack(pady=0)
        self.header_label.place(x=0, y=0, height=66)
        x = 0
        for i in range(len(texts2)):
            x += 150
            bg = tk.Button(self.header_label, text=list(texts2)[i],borderwidth=0,highlightbackground="white",highlightthickness=0, anchor='center', bg="#005CFB", fg="white", width=13,command=texts2[list(texts2)[i]])

            # bg.config(command=texts2[list(texts2)[i]])
            bg.place(x=root.winfo_width() - x, y=15)


#########################################################################################################3

    def new_label(self,texts1,size,pos):

        for i in range(len(texts1)):
        #     values=
             self.comboExample = ttk.Combobox(self.header_label,state="readonly",
                                         values=[i for i in texts1[list(texts1)[i]]],width=size[i])
             self.comboExample.set(list(texts1)[i])
        #
        #
        #
             self.comboExample.pack()
             self.comboExample.bind("<<ComboboxSelected>>", self.justamethod)
        #
             self.comboExample.place(x=pos[i], y=20)
        #

    def justamethod(self,event=None):
      if event:

        print(event.widget.get())


    ##########################################################################################################
    def new_checkBox(self,texts3):
        x=360
        for i in range(len(texts3)):
            CheckVar1 = IntVar()


            self.C = Checkbutton(self.header_label, text=list(texts3)[i], variable=CheckVar1, onvalue=1, offvalue=0, bg=self.topbar_colour,fg="black")
            self.C.pack()
            self.C.place(x=x, y=30)
            x = x + 66

###########################################################################################################

class MyTable(Table):

        def __init__(self, parent=None, **kwargs):
                Table.__init__(self, parent, **kwargs)
                return

        def make_table(frame,file, **kwds):
            """make a sample table"""
            excel = file
            df = pd.read_excel(excel, skipinitialspace=True,encoding='utf-8')
            pt = MyTable(frame, dataframe=df, **kwds)
            pt.show()
            return pt


######################################################################################################
class ToggledFrame(tk.Frame):

    def __init__(self, parent,bg,image, text="", *args, **options):
        tk.Frame.__init__(self, parent, *args, **options)


        self.title_frame = ttk.Frame(self)
        self.title_frame.pack()
        self.color=bg

        self.toggle_button = tk.Button(self.title_frame,image=image,compound="left",text=" "+text,anchor='w', bg=self.color, fg="white",height=20,
                                       highlightbackground="black",borderwidth=0, padx=15,font=("IBMPlexSans",9))
        self.toggle_button.config(command=partial(lambda *args: self.toggle(self.toggle_button,self.color,image),self.toggle_button))

        self.toggle_button.grid(row=3,column=0,ipady=20,sticky='nesw',ipadx=66)
        self.toggle_button.image=image

        self.sub_frame = tk.Frame(self, relief="sunken")

    def toggle(self,button,color,image):
        global high,x,img,Manage_test_script
        if high and  high is not button :

            high.config(fg="white",image=img, font=("IBMPlexSans", 9),bg=self.color)
            high.image=img
            self.sub_frame.forget()

        high = button
        self.color=color
        img=image
        txt=button.config('text')[-1][:-1]

        if  button.cget('bg')  in["#1163F7"]:
            if txt.strip()== "Manage Test Scripts":
              img1 = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image33.png"))
            else:
                img1 = ImageTk.PhotoImage(Image.open("C:\\Users\\LIJAMANNATHARA\\Downloads\\image66.png"))


            button.config(fg="#1163F7",image=img1,compound='left',font=('IBMPlexSans',9,'bold'),bg="white")
            button.image=img1

            self.sub_frame.pack()
            root.update()
            self.header_label = tk.Frame(root, bg='#d5d5d5', width=root.winfo_width(), height=10)
            self.header_label.pack(pady=0)
            self.header_label.place(x=0, y=0, height=66)

        else:

            button.config(fg="white",image=img,compound='left',font=('IBMPlexSans',9),bg=self.color)
            button.image=img
            self.sub_frame.forget()
            root.update()
            self.header_label = tk.Frame(root, bg="#d5d5d5", width=root.winfo_width(), height=10)
            self.header_label.pack(pady=0)
            self.header_label.place(x=0, y=0, height=66)


#############################################################################################


class GradientFrame(tk.Canvas):
    '''A gradient frame which uses a canvas to draw the background'''
    def __init__(self, parent, color1="red", color2="black", **kwargs):
        tk.Canvas.__init__(self, parent, **kwargs)
        self._color1 = color1
        self._color2 = color2
        self.bind("<Configure>", self._draw_gradient)

    def _draw_gradient(self, event=None):
        '''Draw the gradient'''
        self.delete("gradient")
        width = self.winfo_width()
        height = self.winfo_height()
        limit = 1000
        (r1,g1,b1) = self.winfo_rgb(self._color1)
        (r2,g2,b2) = self.winfo_rgb(self._color2)
        r_ratio = float(r2-r1) / limit
        g_ratio = float(g2-g1) / limit
        b_ratio = float(b2-b1) / limit

        for i in range(height):
            nr = int(r1 + (r_ratio * i))
            ng = int(g1 + (g_ratio * i))
            nb = int(b1 + (b_ratio * i))
            color = "#%4.4x%4.4x%4.4x" % (nr,ng,nb)
            self.create_line(0,i,height,i, tags=("gradient",), fill=color)
        self.lower("gradient")



##############################################################################################3


# root window created. Here, that would be the only window, but
# you can later have windows within windows.


def center(e):
    w = int(root.winfo_width() / 5) # get root width and scale it ( in pixels )
    s = 'IBM UI4AutomationFrameworks'.rjust(w//2)
    root.title(s)



root = Tk()
root.bind("<Configure>", center)
# root.configure(bg="#444546")
# root.update()

# width = 500
# #
# height = 500
# root.geometry("%dx%d" % (width, height))
root.state('zoomed')
# root.resize(False,False)
# root.resizable(False,False)
root.iconbitmap(default='C:\\Users\\LIJAMANNATHARA\\Downloads\\Icon1.ico')

high,img="",""
x=False
Record,Generate,Manage_test_script,Maintain_test_set,Test_management,Test_execution=True,True,True,True,True,True
# creation of an instance
app = Window(root)


# mainloop
root.mainloop()
