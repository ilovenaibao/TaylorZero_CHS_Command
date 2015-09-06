echo uninstall apk......
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


:: ************** Install apk & start Activity class******************

set Start_Activity_Name=com.besta.app.realteachingtestcallapp.TestaCallApp

:: *******************************************************************

::help
echo ------------------------------------
echo.
echo Help:
call adb.exe devices
echo pkg_name: %ProjectPackage_Name%
echo apk_name: .\%Project_Name%\%ProjectOutApk_Name%
echo command : "adb -s <device_number> install -r .\%Project_Name%\%ProjectOutApk_Name%"
echo.
echo ------------------------------------


:: uninstall old apk which is on the device
echo.
echo Uninstalling old apk ...
call adb.exe uninstall %ProjectPackage_Name%
if errorlevel 1 goto ERROR
goto EXIT_END

:EXIT_END
echo.
pause
exit

:ERROR
echo.
echo some error occurred, please check!
goto EXIT_END
