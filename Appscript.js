function doGet(e){


 var ss = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1IMSZIld6qgGtkUTAypiTajIGhuSd9NfVKyOUqoKpil4/edit#gid=0");

 var sheet = ss.getSheetByName("Sheet1");
  
 return getUsers(sheet); 
 return 
}


function getUsers(sheet){
  var jo = {};
  var dataArray = [];


  var rows = sheet.getRange(2,1,sheet.getLastRow()-1, sheet.getLastColumn()).getValues();
  
  for(var i = 0, l= rows.length; i<l ; i++){
    var dataRow = rows[i];
    var record = {};
    record['headlines'] = dataRow[0];
    record['links'] = dataRow[1];
    record['img_url'] = dataRow[2];
    record['description'] = dataRow[3];
    
    dataArray.push(record);
    
  }  
  
  jo.user = dataArray;
  
  var result = JSON.stringify(jo,undefined,4);
  
  return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
  
}  
