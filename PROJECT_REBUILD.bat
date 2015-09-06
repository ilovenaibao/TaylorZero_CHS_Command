echo rebuild......
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

:: delete old compile output info 
::del /s /q %Compile_Info_Path%\*.*
echo.
echo create compile output info folder ...
cd %Project_Path%
cd ..
rmdir /s /q %Compile_Info_Path%
mkdir %Compile_Info_Path%

:: start rebuild a android project

:: 1. �h����һ��build�ļ�
echo.
echo delete old build files ...
cd %Project_Path%
rmdir /s /q gen
rmdir /s /q bin
mkdir gen
mkdir bin

:: 2. ����R.java�ļ���������Ҫ����gen�ļ��A
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

:: 3. ����.class�ļ�
:: eg> javac -encoding UTF-8 -target 1.6 -bootclasspath %AndroidJar_Path% -d bin (-cp [path]�����ĵ�����jar) src\com\besta\app\cmdtest\*.java gen\com\besta\app\cmdtest\R.java -classpath libs\baidumapapi.jar
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo make "*.class" file...
echo make "*.class" file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
call javac -Xlint -encoding %Javac_Encode% -target %JavacSDK_Ver% -bootclasspath %AndroidJar_Path% -d bin -cp @..\COMPILE_LIB_FILES -sourcepath @..\COMPILE_SOURCE_FILES 1>> %Compile_Info_Full_Path%\%Compile_Info_File_Name% 2>&1
:: 1>>%Compile_Info_Full_Path%\%Compile_Info_File_Name% 2>>&1
:: >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR_JAVAC

:: 4. ����.dex�ļ�
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo make "*.dex" file...
echo make "*.dex" file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
call dx.bat --dex --output=%Project_Path%\bin\classes.dex %Project_Path%\bin .\libs\*.jar >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: 5. ����aapt ����*.ap_�ļ�, ������YԴ�ļ�
:: ��춛]��assets�ļ��A����ˈ���֮ǰ��Ҫ����assets
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo package "*.ap_" for some resouse file...
echo package "*.ap_" for some resouse file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
call aapt.exe package -f -M AndroidManifest.xml -S res -I %AndroidJar_Path% -A assets -F %Project_Path%\bin\%ProjectRes_Name% >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: 6. ����δ������apk�ļ�
echo.
echo. >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
echo make unsigner apk file...
echo make unsigner apk file... >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
REM call java.exe -classpath F:\TaylorGu_Install\Android\adt-bundle-windows-x86-20140321\adt-bundle-windows-x86-20140321\sdk\tools\lib\sdklib.jar com.android.sdklib.build.ApkBuilderMain %*
call apkbuilder.bat %Project_Path%\bin\%ProjectUnsigedApk_Name% -v -u -z %Project_Path%\bin\%ProjectRes_Name% -f %Project_Path%\bin\classes.dex -rf %Project_Path%\src -nf %Project_Path%\libs -rj %Project_Path%\libs >> %Compile_Info_Full_Path%\%Compile_Info_File_Name%
if errorlevel 1 goto ERROR

:: 8. ��jarsigner ����cmdTest_unsigner.apk����Target apk : cmdTest.apk
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
