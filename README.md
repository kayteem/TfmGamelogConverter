# Terraforming Mars: GameLog Converter v1.5.52

## Scope
- This application allows for converting the Terraforming Mars GameLog JSON files  
(created by the TFM MOD: "Overlay.dll") into an Excel file (xlsx format).

## Download

You may [download the tool here](https://github.com/kayteem/TfmGamelogConverter/blob/main/executable/TfmGamelogConverter%20v1.5.zip) for free.
         
## Screenshots
Plays:
![Screenshot_Plays](https://github.com/kayteem/TfmGamelogConverter/blob/main/doc/Screenshot_Plays.PNG?raw=true)
      
Corps:
![Screenshot Corps](https://github.com/kayteem/TfmGamelogConverter/blob/main/doc/Screenshot_Corps.PNG?raw=true)    

Corps (with Tharsis Filter):
![Screenshot Corps_Filtered](https://github.com/kayteem/TfmGamelogConverter/blob/main/doc/Screenshot_Corps_Filtered.PNG?raw=true)


## QuickStart Guide
- This application requires no installation.
- Simply download the .zip archive and extract the contents (.jar file and .bat file)  
  to whereever your GameLog JSON files are located at, which usually should be at  
  Steam\SteamApps\common\Terraforming Mars\Logs\GameLogs.
- In order to run the application, simply double-click the extracted batch file (.bat)
- As a result:
  - the application (.jar) will be started
  - all JSON files in this directory (and all its sub-directories) will be imported
  - the application creates an Excel file, containing the summarized data of all imported JSON files


## Requirements
- This is a KOTLIN/JAVA application.
- It requires a valid Java installation in order to run.


## Check Java Installation
- Most computers have Java pre-installed.
- On windows OS, you can check it like this:


    1) Hit [Windows Key] + [R]  
    > The "run" box appears
  
    2) Type in "cmd" and click "ok"  
    > This will open the Command Prompt
  
    3) Type in "java -version"  
    > If the console shows version information, then Java is installed and you are ready to go.
   

- If you haven't installed Java yet, you can find the Java download for all operating systems here:
  https://www.java.com/en/download/manual.jsp


## Version History
- V1.5 [2023_02_20]: added "Players" and "Winner" columns to "Plays" tab, added win rates to "Corps" tab
- V1.4 [2023_02_16]: "Plays" tab now color-codes victory/defeat, added new "Corps" tab showing play counts of corporations
- V1.3 [2023_02_15]: application now properly formats all cells and highlights the user's player name for every play
- V1.2 [2023_01_22]: application now exports to Excel, and contains corporation and ELO rating data
- V1.1 [2023_01_21]: application now also exports the player ELO ratings
- v1.0 [2023_01_20]: basic functionality for exporting time/board/players/scores/generations into a CSV file
