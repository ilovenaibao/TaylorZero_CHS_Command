echo create project......
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
:: start create a android project

:: 1. 創建一個工程
:: -n 工程名 : cmdTest
:: -p 工程存儲路徑 : .\cmdTestProject
:: -k 工程包名 : com.besta.app.cmdtest
:: -a Activity子類名 : CmdTestActivity
:: -t 工程使用的平臺 : 10, 如果不知道可以使用Android.bat list target 來查找
::echo create project...
call android.bat create project -n %Project_Name% -p %Project_Name% -k %ProjectPackage_Name% -a %Project_Name% -t %ProjectSDK_ID%
if errorlevel 1 goto ERROR

:: 2. 生成R.java文件，首先需要創建gen文件夾
echo make "R.java" file...
cd %Project_Name%
mkdir gen
call aapt.exe p -f -m -J gen -S res -I %AndroidJar_Path% -M AndroidManifest.xml
if errorlevel 1 goto ERROR

:: 3. 生成.class文件
:: eg> javac -encoding UTF-8 -target 1.6 -bootclasspath %AndroidJar_Path% -d bin src\com\besta\app\cmdtest\*.java gen\com\besta\app\cmdtest\R.java -classpath libs\baidumapapi.jar
echo make "*.class" file...
call javac -encoding %Javac_Encode% -target %JavacSDK_Ver% -bootclasspath %AndroidJar_Path% -d bin src\%ProjectInner_Path%\*.java gen\%ProjectInner_Path%\R.java
if errorlevel 1 goto ERROR

:: 4. 生成.dex文件
echo make "*.dex" file...
call dx.bat --dex --output=%Project_Path%\bin\classes.dex %Project_Path%\bin
if errorlevel 1 goto ERROR

:: 5. 利用aapt 生成*.ap_文件, 即打包資源文件
:: 由於沒有assets文件夾，因此執行之前需要建立assets
echo package "*.ap_" for some resouse file...
mkdir assets
call aapt.exe package -f -S res -I %AndroidJar_Path% -A assets -M AndroidManifest.xml -F %Project_Path%\bin\%ProjectRes_Name%
if errorlevel 1 goto ERROR

:: 6. 生成未簽名的apk文件
echo make unsigner apk file...
call apkbuilder.bat %Project_Path%\bin\%ProjectUnsigedApk_Name% -v -u -z %Project_Path%\bin\%ProjectRes_Name% -f %Project_Path%\bin\classes.dex -rf %Project_Path%\src -nf %Project_Path%\libs -rj %Project_Path%\libs
if errorlevel 1 goto ERROR

:: 7. 用JAVA SDK提供的工具keytools生成簽名的apk文件
:: -dname 參數說明(按順序)：您的---> 名字的姓氏，組織單位名稱，組織名稱，所在域或區域名稱， 州或省份， 所在國家的2位國家代碼
:: 若不寫這些信息則會在cmd運行的過程當中提示輸入信息，因此寫成下面配置好的信息可以直接跳過交互介面
echo make apk's keystore file...
call keytool.exe -genkey -alias %ProjectKeyStore_Name% -keyalg RSA -validity %ValidityDays_Count% -keystore %ProjectKeyStore_Name% -dname "CN=%RD_Name%,OU=%Company_Name%,O=%Organization_Name%,L=%Local_Name%,ST=%Province_Name%,C=%Country_ID%" -keypass %MakeApk_Pass% -storepass %KeystoreMain_Pass%
if errorlevel 1 goto ERROR

:: 8. 用jarsigner 簽名cmdTest_unsigner.apk生成Target apk : cmdTest.apk
echo signed that unsigner apk file to make a *.apk file...
call jarsigner.exe -verbose -keystore %ProjectKeyStore_Name% -keypass %MakeApk_Pass% -storepass %KeystoreMain_Pass% -signedjar %ProjectOutApk_Name% %Project_Path%\bin\%ProjectUnsigedApk_Name% %ProjectKeyStore_Name% %MakeApk_Pass%
if errorlevel 1 goto ERROR
goto EXIT_SUCCESS

:ERROR
if exist %Success_Info% del %Success_Info%
echo some error occurred, please check!
goto EXIT_END

:EXIT_SUCCESS
echo make %ProjectOutApk_Name% success!
:: *******************************************************************************************************************************
:: OUTPUT Infomation and print in a file -------> make project's info
@echo off
echo ---------------------- > %Success_Info%
echo Total infomation:    * >> %Success_Info%
echo ---------------------- >> %Success_Info%
echo *************Project Info*************************************** >> %Success_Info%
echo Project path   : %Project_Path% >> %Success_Info%
echo Project Name   : %Project_Name% >> %Success_Info%
echo Package Name   : %ProjectPackage_Name% >> %Success_Info%
echo add-ons        : %ProjectSDK_ID% >> %Success_Info%
echo keystore       : %ProjectKeyStore_Name% >> %Success_Info%
echo keystore pass  : %MakeApk_Pass% >> %Success_Info%
echo keystore main pass: %KeystoreMain_Pass% >> %Success_Info%
echo Validity Days  : %ValidityDays_Count% >> %Success_Info%
echo *************Apk's Info***************************************** >> %Success_Info%
echo apk's Name     : %ProjectOutApk_Name% >> %Success_Info%
echo apk's Path     : %Project_Path%\%ProjectOutApk_Name% >> %Success_Info%
echo *************Developer Info************************************* >> %Success_Info%
echo Developer Name : %RD_Name% >> %Success_Info%
echo Company Name   : %Company_Name% >> %Success_Info%
echo Organization   : %Organization_Name% >> %Success_Info%
echo Country ID     : %Country_ID% >> %Success_Info%
echo *************END************************************************ >> %Success_Info%
:: save in file
::call start /wait %Success_Info%
@echo off
echo ----------------------------------------------------------------
echo Total infomation:                                              ^|
echo ----------------------------------------------------------------
echo *************Project Info***************************************
echo Project path   : %Project_Path%
echo Project Name   : %Project_Name%
echo Package Name   : %ProjectPackage_Name%
echo add-ons        : %ProjectSDK_ID%
echo keystore       : %ProjectKeyStore_Name%
echo keystore pass  : %MakeApk_Pass%
echo keystore main pass: %KeystoreMain_Pass%
echo Validity Days  : %ValidityDays_Count%
echo *************Apk's Info*****************************************
echo apk's Name     : %ProjectOutApk_Name%
echo apk's Path     : %Project_Path%\%ProjectOutApk_Name%
echo *************Developer Info*************************************
echo Developer Name : %RD_Name%
echo Company Name   : %Company_Name%
echo Organization   : %Organization_Name%
echo Country ID     : %Country_ID%
echo *************END************************************************
echo You can also see output file in :
echo %Success_Info%
goto EXIT_END

:EXIT_END
pause
exit
