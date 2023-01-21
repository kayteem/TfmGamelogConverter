# Terraforming Mars: GameLog Converter

## Scope
- This application allows for converting the Terraforming Mars GameLog JSON files  
(created by the TFM MOD: "Overlay.dll") into a single CSV file.

## Requirements
- This is a JAVA application.
- It requires a valid Java installation in order to run.

## QuickStart Guide
- This application requires no installation.
- Simply download the .jar file and the .bat file from the "executable" directory  
and copy them to whereever your GameLog JSON files are located.
- In order to run the application, simply double-click the .bat file
- As a result,
  - the application (.jar) will be started
  - all JSON files in this directory (and all its sub-directories) will be imported
  - the application creates a summary CSV file, containing the data of all imported JSON files
  
## Tipps & Tricks
- CSV files can be opened with Microsoft Excel, but they are not displayed in tabular form by default
- Instead, Excel will put all data into a single column
- To fix this,
  - first select the first column (containing all the data)
  - then use Excel's "Text to Columns" feature, located in the "Data" group of the toolbar 
