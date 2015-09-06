My_Static_Method_Lib
====================
  
All using static method  
This project is using by others projects for some static method.  
The mehods has a struct with followed:  

      com.
         |--android.
                   |--mylib.
                           |--dbtypeconvert.
                                           |--MyDBTypeConvert.java : defined {
                                              #Some data type convert for exmaple : int to byte , byte to string.
                                              dip2px : dip convert pix.
                                              px2dip : px convert dip.
                                              Str4Bytes2Int : byte[4] convert int.
                                              nBytes2Str : byte[n] convert string, it is very important for n%2 == 0.
                                            }
                           |--dragpapers.
                                        |--MyDragPapersScrollLayout.java : defined {
                                           #This package is defined one ViewGroup, it is modeled Android Launcher's workspace.
                                           #It can slide left and right from pre or next view
                                        }
                           |--exceptioninfo.
                                           |--MyErrorPrintfActivity.java : defined {
                                              #This package defined some error show on the screen when run android application.
                                              #It is very important for debug mode.
                                           }
                                           |--MyExceptionInfo.java : defined {
                                              #Defined MyErrorPrintfActivity class of functions.
                                           }
                           |--graphic.
                                     |--MyGraphic.java : defined {
                                        #Defined graphics functions. For example some functions method.
                                        getBitmapFromResources : get bitmap from activity res of R.id.*
                                        convertBytes2Bimap : convert bitmap of byte[] to bitmap type
                                        convertBitmap2Bytes : convert bitmap type to byte[]
                                        convertDrawable2BitmapByCanvas : Drawable type to Bitmap type
                                        convertBitmap2Drawable : Bitmap type to Drawable type
                                        loadBitmapAutoSize : load a bitmap auto resize width and height
                                        loadingOnePic : load one bitmap from file
                                        BitmapRatioMatrix : matrix one bitmap's width and height
                                        SaveResultPicBuffer : save a bitmap to one file
                                     }
                           |--loadingview.
                                         |--MyLoadingView.java : defined {
                                            #When loading one view and there are so much data, it is waiting for read. It is
                                            #like Android progress dialog.
                                            this package defined a kind of loading type
                                         }
                           |--loginfo.
                                     |--MyLogInfo.java : defined {
                                        #This package is for debug android apps. When you don't for debugging, you can see
                                        #log info in emulator.
                                     }
                           |--media.
                                   |--PlayMediaTime.java : defined {
                                      #Class for convert long type to time[hh:mm:ss] type
                                   }
                                   |--PlayMp3Class.java : defined {
                                      #Test play mp3
                                   }
                                   |--PlayMp4Class.java : defined {
                                      #Test play mp4
                                   }
                           |--network.
                                     |--MyWifiInfo.java : defined {
                                        #Checking current network or WiFi is connect.
                                     }
                           |--screen.
                                    |--MyLibScreenInfo.java : defined {
                                      #This class defined struct for screen for example: screen width and height.
                                    }
                                    |--MyLibScreenSetting.java : defined {
                                      #This class defined method for setting and get android machine's screen info
                                      SettingScreenHorizontal : setting android activity horizontal show
                                      SettingScreenShowTheme : setting android activity full screen or no titlebar
                                      GetScreenSize : get this machine's screen size
                                    }
                           |--staticmethod.
                                          |--My_Static_Method_Lib.java : defined {
                                             #This class defined some cannot divide to group.
                                             getResAbsolutePath : get one file absolue path
                                          }
                           |--xscan.
                                   |--FileTypeFilter.java : defined {
                                      #Filter like *.png or *.txt file.
                                      #This is extends android FilenameFilter class
                                   }
                             
                             
All of method detail info is above. It is using in any my android projects, so if you want download my android projects 
and build it include this lib.

