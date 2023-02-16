# Terraforming Mars: GameLog Converter v1.4

## Scope
- This application allows for converting the Terraforming Mars GameLog JSON files  
(created by the TFM MOD: "Overlay.dll") into an Excel file (xlsx format).
                 
## Screenshots
Plays:
![Screenshot_Plays](https://github.com/kayteem/TfmGamelogConverter/blob/main/doc/Screenshot_Plays.PNG?raw=true)
      
Corps:
![Screenshot Corps](https://github.com/kayteem/TfmGamelogConverter/blob/main/doc/Screenshot_Corps.PNG?raw=true)    

Corps (filtered for Tharsis map):
![Screenshot Corps_Filtered](https://github.com/kayteem/TfmGamelogConverter/blob/main/doc/Screenshot_Corps_Filtered.PNG?raw=true)

## Requirements
- This is a KOTLIN/JAVA application.
- It requires a valid Java installation in order to run.

## QuickStart Guide
- This application requires no installation.
- Simply download the [.zip archive](https://github.com/kayteem/TfmGamelogConverter/blob/main/executable/TfmGamelogConverter%20v1.4.zip) and extract the contents (.jar file and .bat file)  
  to whereever your GameLog JSON files are located at, which usually should be at  
  Steam\SteamApps\common\Terraforming Mars\Logs\GameLogs.
- In order to run the application, simply double-click the extracted batch file (.bat)
- As a result:
  - the application (.jar) will be started
  - all JSON files in this directory (and all its sub-directories) will be imported
  - the application creates an Excel file, containing the summarized data of all imported JSON files

## Version History
- V1.4 [2023_02_16]: "Plays" tab now color-codes victory/defeat, added new "Corps" tab showing play counts of corporations
- V1.3 [2023_02_12]: application now properly formats all cells and highlights the user's player name for every play
- V1.2 [2023_01_22]: application now exports to Excel, and contains corporation and ELO rating data
- V1.1 [2023_01_21]: application now also exports the player ELO ratings
- v1.0 [2023_01_20]: basic functionality for exporting time, board, player names and scores, as well as the number of generations into a CSV
