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
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";
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
var btnContainer = document.getElementById("myBtnContainer");
var btns = btnContainer.getElementsByClassName("tabButton");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function(){
        var current = document.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        this.className += " active";
    });
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

//========== SHOPPING CART PAGE ========
function addToCart(id){
    var cartItem = {
        id,
        product: null,
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
        product: null,
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
    msgDiv.id = "add_to_cart_msg";
    var divStyle = "height: 25px; width: 100%; background-color:#08607a; color:white;\
    position:fixed; bottom: 0; left: 0; right: 0; z-index: +1; text-align: center;\
    border: white solid 1px; border-radius: 2px;"
    msgDiv.style.cssText = divStyle;
    msgDiv.innerHTML="<p>" + msg + "</p>"
    document.body.appendChild(msgDiv);
    setTimeout(function (){document.getElementById("add_to_cart_msg").remove();}, 2000);
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
    //alert(divId);
    document.getElementById(divId).remove();
    //if all the items have been removed from the cart, refresh the page
    var itemsDisplayed = document.getElementById("cart_items_panels_id").childElementCount;
    if(itemsDisplayed === 0){
        location.reload();
    }
}

function checkoutAlert(){
    var msg = "&diams; Please log in to checkout. Thank you &diams;";
    displayMessage(msg);
}