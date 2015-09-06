echo uninstall apk......
@echo off
:: *******************************************************************************************************************************
::     **********************************************Ո�J����x�������ã�*****************************************************
::        ******************************************��t���̄������ܳ��e**************************************************
rem readme
rem (*) ----> ����޸Ļ�����ă���
rem (/) ----> ����ʹ��Ĭ�Jֵ
rem ���apk���ɳɹ������N���ڹ��̸�Ŀ������ɣ�make_project_info.txt
rem �@��txt��ӛ䛵��ǹ������P��Ϣ
rem END
:: *******************************************************************************************************************************
:: �M�й�������

:: 0. �O������·�� ----> Initialize some info

:: ��������
:: (*) ������ (*)
set Project_Name=%1
:: (*) ���̰��� (*)
set ProjectPackage_Last_Name=%2
set ProjectPackage_Name=%3
:: (*) ��������adk-addon target ID (*)
set ProjectSDK_ID=%4
:: (/) ���̴�����YԴ�ļ��� (/)
set ProjectRes_Name=%5
:: (/) �������ɵ�δ������apk�� (/)
set ProjectUnsigedApk_Name=%6
:: (/) ������Kݔ����apk�� (/)
set ProjectOutApk_Name=%7
:: *******************************************************************************************************************************

:: keystoreʹ�õĂ�����Ϣ����
:: (/) �������ɵ�keystore�� (/)
set ProjectKeyStore_Name=%8
:: (*) ���ɵ�keystore��Ч�����씵 (*)
set ValidityDays_Count=%9
:: (/) ����������� (/)
SHIFT /8
set RD_Name=%9
:: (/) ��ĽM����λ���Q (/)
SHIFT /8
set Company_Name=%9
:: (/) ��ĽM�����Q (/)
SHIFT /8
set Organization_Name=%9
:: (/) ���������^�����Q (/)
SHIFT /8
set Local_Name=%9
:: (/) �����ڵ��ݻ�ʡ�� (/)
SHIFT /8
set Province_Name=%9
:: (/) �����ڇ��ҵ�2λ���Ҵ��a (/)
SHIFT /8
set Country_ID=%9
:: (/) �O�����ɺ�����apk���ܴa (/)
SHIFT /8
set MakeApk_Pass=%9
:: (/) �O��keystore���ܴa(һ����Ժ�apk�����ܴaһ��) (/)
SHIFT /8
set KeystoreMain_Pass=%9
:: *******************************************************************************************************************************

:: Java & Android SDK ·������
:: (/) �O��javac���g�r��*.java �ļ��ľ��a��ʽ (/)
SHIFT /8
set Javac_Encode=%9
:: (/) �O��javac���g�r��SDK�汾 (/) %19
SHIFT /8
set JavacSDK_Ver=%9
:: (*) ����·�� (*) %20
SHIFT /8
set Project_Parent_Path=%9
SHIFT /8
set Project_Path=%9
:: (*) ���̃Ȳ�����·�� (*) %22
SHIFT /8
set ProjectInner_Path=%9
:: (*) JavaSDK·�� (*) %23
SHIFT /8
set JavaSDK_Path=%9
:: (*) AndroidSDK·�� (*) %24
SHIFT /8
set AndroidJar_Path=%9
:: (/) ���̄����ɹ������P��Ϣ�� (/) %25
SHIFT /8
set Success_Info=%9
:: (/) ���̾��g��ϢOutPut�ļ�·�� (/) %26
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
