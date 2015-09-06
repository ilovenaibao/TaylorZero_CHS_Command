echo rebuild......
@echo off
:: *******************************************************************************************************************************
::     **********************************************請認真閱讀工程配置！*****************************************************
::        ******************************************否則工程創建可能出錯**************************************************
rem readme
rem (*) ----> 必須修改或者填寫的內容
rem (/) ----> 可以使用默認值
rem 如果apk生成成功，那麼會在工程根目錄下生成：make_project_info.txt
rem 這個txt中記錄的是工程相關信息
rem END
:: *******************************************************************************************************************************
:: 進行工程配置

:: 0. 設置所需路徑 ----> Initialize some info

:: 工程配置
:: (*) 工程名 (*)
set Project_Name=%1
:: (*) 工程包名 (*)
set ProjectPackage_Last_Name=%2
set ProjectPackage_Name=%3
:: (*) 工程所用adk-addon target ID (*)
set ProjectSDK_ID=%4
:: (/) 工程打包的資源文件名 (/)
set ProjectRes_Name=%5
:: (/) 工程生成的未簽名的apk名 (/)
set ProjectUnsigedApk_Name=%6
:: (/) 工程最終輸出的apk名 (/)
set ProjectOutApk_Name=%7
:: *******************************************************************************************************************************

:: keystore使用的個人信息配置
:: (/) 工程生成的keystore名 (/)
set ProjectKeyStore_Name=%8
:: (*) 生成的keystore有效日期天數 (*)
set ValidityDays_Count=%9
:: (/) 你的名字姓氏 (/)
SHIFT /8
set RD_Name=%9
:: (/) 你的組織單位名稱 (/)
SHIFT /8
set Company_Name=%9
:: (/) 你的組織名稱 (/)
SHIFT /8
set Organization_Name=%9
:: (/) 你所在域或區域名稱 (/)
SHIFT /8
set Local_Name=%9
:: (/) 你所在的州或省份 (/)
SHIFT /8
set Province_Name=%9
:: (/) 你所在國家的2位國家代碼 (/)
SHIFT /8
set Country_ID=%9
:: (/) 設置生成簽名的apk的密碼 (/)
SHIFT /8
set MakeApk_Pass=%9
:: (/) 設置keystore主密碼(一般可以喝apk簽名密碼一致) (/)
SHIFT /8
set KeystoreMain_Pass=%9
:: *******************************************************************************************************************************

:: Java & Android SDK 路徑配置
:: (/) 設置javac編譯時的*.java 文件的編碼格式 (/)
SHIFT /8
set Javac_Encode=%9
:: (/) 設置javac編譯時的SDK版本 (/) %19
SHIFT /8
set JavacSDK_Ver=%9
:: (*) 工程路徑 (*) %20
SHIFT /8
set Project_Parent_Path=%9
SHIFT /8
set Project_Path=%9
:: (*) 工程內部所用路徑 (*) %22
SHIFT /8
set ProjectInner_Path=%9
:: (*) JavaSDK路徑 (*) %23
SHIFT /8
set JavaSDK_Path=%9
:: (*) AndroidSDK路徑 (*) %24
SHIFT /8
set AndroidJar_Path=%9
:: (/) 工程創建成功的相關信息名 (/) %25
SHIFT /8
set Success_Info=%9
:: (/) 工程編譯信息OutPut文件路徑 (/) %26
SHIFT /8
set Compile_Info_Path=%9
SHIFT /8
set Compile_Info_Full_Path=%9
SHIFT /8
set Compile_Info_File_Name=%9
set Compile_OutPut_Command=>> %Compile_Info_Full_Path%\%Compile_Info_File_Name%

:: *******************************************************************************************************************************

:: delete old compile output info 
::del /s /q %Compile_Info_Path%\*.*
echo.
echo create compile output info folder ...
cd %Project_Path%
cd ..
rmdir /s /q %Compile_Info_Path%
mkdir %Compile_Info_Path%

:: start rebuild a android project

:: 1. 刪除上一次build文件
echo.
echo delete old build files ...
cd %Project_Path%
rmdir /s /q gen
rmdir /s /q bin
mkdir gen
mkdir bin

:: 2. 生成R.java文件，首先需要創建gen文件夾
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo Remake "R.java" file...
echo Remake "R.java" file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%

::call aapt.exe p -f -m --auto-add-overlay -J gen  -M G:\Android_Libs_Projects\My_Static_Method_Lib\AndroidManifest.xml -S G:\Android_Libs_Projects\My_Static_Method_Lib\res -I %AndroidJar_Path%

call aapt.exe p -f -m --auto-add-overlay -J gen -M AndroidManifest.xml -S res -I %AndroidJar_Path% >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: MyLib
echo make My_Static_Method_Lib "R.java"...
rmdir /s /q lib_proj\My_Static_Method_Lib\gen
mkdir lib_proj\My_Static_Method_Lib\gen
call aapt.exe p -f -m --auto-add-overlay -J lib_proj\My_Static_Method_Lib\gen -M lib_proj\My_Static_Method_Lib\AndroidManifest.xml -S lib_proj\My_Static_Method_Lib\res -I %AndroidJar_Path% >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

echo make dslv_library "R.java"...
rmdir /s /q lib_proj\dslv_library\gen
mkdir lib_proj\dslv_library\gen
call aapt.exe p -f -m --auto-add-overlay -J lib_proj\dslv_library\gen -M lib_proj\dslv_library\AndroidManifest.xml -S lib_proj\dslv_library\res -I %AndroidJar_Path% >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: 3. 生成.class文件
:: eg> javac -encoding UTF-8 -target 1.6 -bootclasspath %AndroidJar_Path% -d bin (-cp [path]包含的第三方jar) src\com\besta\app\cmdtest\*.java gen\com\besta\app\cmdtest\R.java -classpath libs\baidumapapi.jar
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo make "*.class" file...
echo make "*.class" file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
call javac -Xlint -encoding %Javac_Encode% -target %JavacSDK_Ver% -bootclasspath %AndroidJar_Path% -d bin -cp @..\COMPILE_LIB_FILES -sourcepath @..\COMPILE_SOURCE_FILES 1>> %Compile_Info_Full_Path%\%Compile_Info_File_Name% 2>&1
:: 1>>%Compile_Info_Full_Path%\%Compile_Info_File_Name% 2>>&1
:: >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR_JAVAC

:: 4. 生成.dex文件
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo make "*.dex" file...
echo make "*.dex" file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
call dx.bat --dex --output=%Project_Path%\bin\classes.dex %Project_Path%\bin .\libs\*.jar >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: 5. 利用aapt 生成*.ap_文件, 即打包資源文件
:: 由於沒有assets文件夾，因此執行之前需要建立assets
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo package "*.ap_" for some resouse file...
echo package "*.ap_" for some resouse file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
call aapt.exe package -f -M AndroidManifest.xml -S res -I %AndroidJar_Path% -A assets -F %Project_Path%\bin\%ProjectRes_Name% >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: 6. 生成未簽名的apk文件
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo make unsigner apk file...
echo make unsigner apk file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
REM call java.exe -classpath F:\TaylorGu_Install\Android\adt-bundle-windows-x86-20140321\adt-bundle-windows-x86-20140321\sdk\tools\lib\sdklib.jar com.android.sdklib.build.ApkBuilderMain %*
call apkbuilder.bat %Project_Path%\bin\%ProjectUnsigedApk_Name% -v -u -z %Project_Path%\bin\%ProjectRes_Name% -f %Project_Path%\bin\classes.dex -rf %Project_Path%\src -nf %Project_Path%\libs -rj %Project_Path%\libs >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: 8. 用jarsigner 簽名cmdTest_unsigner.apk生成Target apk : cmdTest.apk
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo signed that unsigner apk file to make a *.apk file...
echo signed that unsigner apk file to make a *.apk file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
call jarsigner.exe -verbose -keystore %ProjectKeyStore_Name% -keypass %MakeApk_Pass% -storepass %KeystoreMain_Pass% -signedjar %ProjectOutApk_Name% %Project_Path%\bin\%ProjectUnsigedApk_Name% %ProjectKeyStore_Name% >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR
goto EXIT_SUCCESS

:ERROR_JAVAC

start %Compile_Info_Full_Path%
goto ERROR

:ERROR
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo some error occurred, please check!
echo some error occurred, please check! >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
pause
goto EXIT_END

:EXIT_SUCCESS
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo Rebuild project Success!
echo Rebuild project Success! >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
start %Compile_Info_Full_Path%
::pause
goto EXIT_END

:EXIT_END
cd %Project_Path%
cd ..
echo.
pause
exit
