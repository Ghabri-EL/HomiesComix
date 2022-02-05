//======== DETAILS PAGE ========
function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("demo");
    var captionText = document.getElementById("caption");    

    if (n > slides.length) {slideIndex = 1}
    if (n < 1) {slideIndex = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        console.log()
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";
    slides[slideIndex-1].style.height = slides[slideIndex-1].offsetWidth + "px";
    dots[slideIndex-1].className += " active";
    captionText.innerHTML = dots[slideIndex-1].alt;   
}
//======== PRODUCTS PAGE ========
function filterSelection(c) {
    var x, i;
    x = document.getElementsByClassName("column");
    if (c == "all") c = "";
    for (i = 0; i < x.length; i++) {
        remove(x[i], "show");
        if (x[i].className.indexOf(c) > -1) add(x[i], "show");
    }
}

function add(element, name) {
    var i, arr1, arr2;
    arr1 = element.className.split(" ");
    arr2 = name.split(" ");
    for (i = 0; i < arr2.length; i++) {
        if (arr1.indexOf(arr2[i]) == -1) {element.className += " " + arr2[i];}
    }
}

function remove(element, name) {
    var i, arr1, arr2;
    arr1 = element.className.split(" ");
    arr2 = name.split(" ");
    for (i = 0; i < arr2.length; i++) {
        while (arr1.indexOf(arr2[i]) > -1) {
            arr1.splice(arr1.indexOf(arr2[i]), 1);
        }
    }
    element.className = arr1.join(" ");
}


// Add active class to the current button (highlight it)
function setActiveButton(){
    var btnContainer = document.getElementById("myBtnContainer");
    var btns = btnContainer.getElementsByClassName("tabButton");
    for (var i = 0; i < btns.length; i++) {
        btns[i].addEventListener("click", function(){
            var current = document.getElementsByClassName("active");
            current[0].className = current[0].className.replace(" active", "");
            this.className += " active";
        });
    }
}

//======== PROFILE PAGE ========

function viewUser(){
    var xhr = new XMLHttpRequest();
    xhr.onload = addView;
    xhr.open("GET", "/userdetails");
    xhr.send();
}

function addView(){
    var user_details = this.responseText;
    var viewPort= document.getElementById("profile_view");
    viewPort.innerHTML=user_details;
}

function viewOrders(){
    var xhr = new XMLHttpRequest();
    xhr.onload = addOrderView;
    xhr.open("GET", "/viewOrders");
    xhr.send();
}

function addOrderView(){
    var order_view = this.responseText;
    var viewPort= document.getElementById("profile_view");
    viewPort.innerHTML=order_view;
}

function changeOrderStatus(id){
    var status = document.getElementById("change_order_status_"+id).value;
    var xhr = new XMLHttpRequest();
    xhr.onload = changeOrderStatusResponse;
    xhr.open("GET", "/change_order_status/"+id+"/"+status);
    xhr.send();
}

function changeOrderStatusResponse(){
    var response = this.responseText;
    displayMessage(response);
}
function viewSellForm(){
    var xhr = new XMLHttpRequest();
    xhr.onload = returnSellView;
    xhr.open("GET", "/sell_form");
    xhr.send();
}
function returnSellView(){
    var sell_form = this.responseText;
    var viewPort = document.getElementById("profile_view");
    viewPort.innerHTML=sell_form;
}

function viewOrderItems(id){
    var x = document.getElementById("orderItems_"+id);
    if (x.style.display === "none") {
        x.style.display = "flex";
    } else {
        x.style.display = "none";
    }
}

//========== SHOPPING CART PAGE ========
function addToCart(id){
    var cartItem = {
        id,
        quantity : document.getElementById("quantity").value
    }
    var xhr = new XMLHttpRequest();
    xhr.onload = addItemResponse;
    xhr.open("POST", "/addToCart")
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(cartItem));
}

function addItemResponse(){
    var response = this.responseText;
    displayMessage(response);
}
// To add just one item to cart from the catalog(products page)
function addOneToCart(id){
    var cartItem = {
        id,
        quantity : 1
    }
    var xhr = new XMLHttpRequest();
    xhr.onload = addItemResponse;
    xhr.open("POST", "/addToCart")
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(cartItem));
}

//creates a div to display a message, such as if a product has been added to the shopping cart
function displayMessage(msg){
    var msgDiv= document.createElement("div");
    msgDiv.id = "message_bar";
    msgDiv.innerHTML="<p>" + msg + "</p>"
    document.body.appendChild(msgDiv);
    setTimeout(function (){document.getElementById("message_bar").remove();}, 3000);
}

function removeProduct(id){
    var xhr = new XMLHttpRequest();
    xhr.onload = removeProductResponse;
    xhr.open("GET", "/remove_product/" + id)
    xhr.send();
}
function removeProductResponse(){
    var response = this.responseText;
    var divId = "cart_item_" + response;
    document.getElementById(divId).remove();
    //reload to show the new total and display empty cart message if all items are removed
    location.reload();
}
function changeCartQuantity(id){
    var cartItem = {
        id,
        quantity : document.getElementById("item_qty_" + id).value
    }
    var xhr = new XMLHttpRequest();
    xhr.onload = changeQuantityResponse;
    xhr.open("POST", "/change_quantity")
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(cartItem));
}
function changeQuantityResponse(){
    var response = this.responseText;
    if(response != 0){
        document.getElementById("item_qty_" + response).value = 1;
    }
    location.reload();
}

function checkoutAlert(){
    var msg = "&diams; Please log in to checkout. Thank you &diams;";
    displayMessage(msg);
}

function checkoutAlertAdmin(){
    var msg = "&diams; Cannot checkout as an admin. Please use a curstomer account &diams;";
    displayMessage(msg);
}

function checkout(){
    var xhr = new XMLHttpRequest();
    xhr.onload = checkoutResponse;
    xhr.open("GET", "/checkout")
    xhr.send();
}
function checkoutResponse(){
    var response = this.responseText;
    let overlayDiv = document.createElement("div");
    overlayDiv.id = "checkoutOverlay";
    overlayDiv.innerHTML= response;
    document.body.appendChild(overlayDiv);
}
function removePayOverlay(){
    document.getElementById("checkoutOverlay").remove();
}

//======== Functions to edit product details ===========
function editProduct(id){
    var xhr = new XMLHttpRequest();
    xhr.onload = editProductResponse;
    xhr.open("GET", "/editProduct/"+id)
    xhr.send();
}
function editProductResponse(){
    var response = this.responseText;
    let overlayDiv = document.createElement("div");
    overlayDiv.id = "edit_product_overlay";
    overlayDiv.innerHTML= response;
    document.body.appendChild(overlayDiv);
}
function removeEditOverlay(){
    document.getElementById("edit_product_overlay").remove();
}
function confirmProductChanges(id){
    var product = {
        id,
        stock : document.getElementById("edit_prod_stock").value,
        price : document.getElementById("edit_product_price").value,
        description : document.getElementById("edit_prod_description").value
    }
    
    var xhr = new XMLHttpRequest();
    xhr.onload = confirmProductChangesResponse;
    xhr.open("POST", "/confirm_product_changes");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(product));
}

function confirmProductChangesResponse(){
    var response = this.responseText;
    removeEditOverlay();
    location.reload();
    displayMessage(response);
}

function hideProduct(id){
    var xhr = new XMLHttpRequest();
    xhr.onload = hideProductResponse;
    xhr.open("GET", "/hideProduct/"+id)
    xhr.send();
}

function hideProductResponse(){
    var response = this.responseText;
    removeEditOverlay();
    displayMessage(response);
}

//======== Home page ===========

