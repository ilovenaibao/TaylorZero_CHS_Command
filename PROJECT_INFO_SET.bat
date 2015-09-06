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
set Project_Name=TaylorZero
:: (*) ���̰��� (*)
set ProjectPackage_Last_Name=taylorzero
set ProjectPackage_Name=com.android.%ProjectPackage_Last_Name%
:: (*) ��������adk-addon target ID (*)
set ProjectSDK_ID=2
:: (/) ���̴�����YԴ�ļ��� (/)
set ProjectRes_Name=%Project_Name%.ap_
:: (/) �������ɵ�δ������apk�� (/)
set ProjectUnsigedApk_Name=%Project_Name%_unsigner.apk
:: (/) ������Kݔ����apk�� (/)
set ProjectOutApk_Name=.\bin\%Project_Name%.apk
:: *******************************************************************************************************************************

:: keystoreʹ�õĂ�����Ϣ����
:: (/) �������ɵ�keystore�� (/)
set ProjectKeyStore_Name=%Project_Name%.keystore
:: (*) ���ɵ�keystore��Ч�����씵 (*)
set ValidityDays_Count=20000
:: (/) ����������� (/)
set RD_Name=Gu
:: (/) ��ĽM����λ���Q (/)
set Company_Name="TaylorGu_Co."
:: (/) ��ĽM�����Q (/)
set Organization_Name=home
:: (/) ���������^�����Q (/)
set Local_Name="xi_an"
:: (/) �����ڵ��ݻ�ʡ�� (/)
set Province_Name=Shannxi
:: (/) �����ڇ��ҵ�2λ���Ҵ��a (/)
set Country_ID=CN
:: (/) �O�����ɺ�����apk���ܴa (/)
set MakeApk_Pass=taylorzero
:: (/) �O��keystore���ܴa(һ����Ժ�apk�����ܴaһ��) (/)
set KeystoreMain_Pass=%MakeApk_Pass%
:: *******************************************************************************************************************************

:: Java & Android SDK ·������
:: (/) �O��javac���g�r��*.java �ļ��ľ��a��ʽ (/)
set Javac_Encode=UTF-8
:: (/) �O��javac���g�r��SDK�汾 (/)
set JavacSDK_Ver=1.6
:: (*) ����·�� (*)
set Project_Parent_Path=E:\Worksapce\Android\Command_TaylorZero-master\
set Project_Path=%Project_Parent_Path%%Project_Name%
:: (*) ���̃Ȳ�����·�� (*)
set ProjectInner_Path=com\android\%ProjectPackage_Last_Name%
:: (*) JavaSDK·�� (*)
set JavaSDK_Path=C:\"Program Files"\Java\
:: (*) AndroidSDK·�� (*)
set AndroidJar_Path=F:\TaylorGu_Install\Android\adt-bundle-windows-x86-20140321\adt-bundle-windows-x86-20140321\sdk\platforms\android-4.0.4\android.jar
:: (/) ���̄����ɹ������P��Ϣ�� (/)
set Success_Info=%Project_Path%\make_%Project_Name%_ProjectInfo.txt
:: (/) ���̾��g��ϢOutPut�ļ�·�� (/)
set Compile_Info_Path=OutPutInfo
set Compile_Info_Full_Path=%Project_Parent_Path%%Compile_Info_Path%
set Compile_Info_File_Name=out_build_info.txt
set Compile_OutPut_Command=>> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
:: (/) ���f������ (/)
set Input_args=%Project_Name% %ProjectPackage_Last_Name% %ProjectPackage_Name% %ProjectSDK_ID% %ProjectRes_Name% %ProjectUnsigedApk_Name% %ProjectOutApk_Name% %ProjectKeyStore_Name% %ValidityDays_Count% %RD_Name% %Company_Name% %Organization_Name% %Local_Name% %Province_Name% %Country_ID% %MakeApk_Pass% %KeystoreMain_Pass% %Javac_Encode% %JavacSDK_Ver% %Project_Parent_Path% %Project_Path% %ProjectInner_Path% %JavaSDK_Path% %AndroidJar_Path% %Success_Info% %Compile_Info_Path% %Compile_Info_Full_Path% %Compile_Info_File_Name%
:: *******************************************************************************************************************************
if %1 == rebuild_project ( call PROJECT_REBUILD.bat %Input_args%)
if %1 == create_project ( call PROJECT_CREATE.bat %Input_args%)
if %1 == install_apk ( call PROJECT_INSTALL_APK.bat %Input_args% %2)
if %1 == uninstall_apk ( call PROJECT_UNINSTALL_APK.bat %Input_args%)

:END
pause
exit
