# -*- mode: python ; coding: utf-8 -*-

block_cipher = None


a = Analysis(['UIAF_UI_NEW.py'],
             pathex=['C:\\Users\\LIJAMANNATHARA\\PycharmProjects\\UIAF_Selenium\\UIAF-Selenium_Python\\venv'],
             binaries=[],
             datas=[],
             hiddenimports=['py4j.java_collections'],
             hookspath=[],
             runtime_hooks=[],
             excludes=[],
             win_no_prefer_redirects=False,
             win_private_assemblies=False,
             cipher=block_cipher,
             noarchive=False)
pyz = PYZ(a.pure, a.zipped_data,
             cipher=block_cipher)
exe = EXE(pyz,
          a.scripts,
          [],
          exclude_binaries=True,
          name='UIAF_UI_NEW',
          debug=False,
          bootloader_ignore_signals=False,
          strip=False,
          upx=True,
          console=True , icon='Icon1.ico')
coll = COLLECT(exe,
               a.binaries,
               a.zipfiles,
               a.datas,
               strip=False,
               upx=True,
               upx_exclude=[],
               name='UIAF_UI_NEW')
