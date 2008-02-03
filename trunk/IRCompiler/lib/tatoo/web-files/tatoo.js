function myInclude(id,file) {
   pre = document.getElementById(id);
   req = new XMLHttpRequest();
   req.open("GET",file,false);
   req.send("");
   text = req.responseText;
   pre.appendChild(document.createTextNode(text));
}
