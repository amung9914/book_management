document.addEventListener('DOMContentLoaded', function(){

    /* 도서 상세 정보를 조회합니다 */

    const bookId = document.getElementById("book-id").value;

    function successForDetail(response){
        const responseData = response.data;
        document.getElementById("bookName").value = responseData.bookName;
        document.getElementById("author").value = responseData.author;

    }

    function fail(){
        alert("도서 정보가 존재하지 않습니다.");
        location.replace("/");
    }

    httpRequestGet(`/detailView/${bookId}`,successForDetail,fail)


    const updateForm = document.getElementById('update-book-form');
    if(updateForm){
        /* 도서 수정 */
        updateForm.addEventListener('submit',function (event){
            event.preventDefault();

            body = JSON.stringify({
                "bookId" : bookId,
                "bookName" : document.getElementById("bookName").value,
                "author" : document.getElementById("author").value
            });

            function success(){
                alert("도서 수정이 완료되었습니다.");
                location.replace("/");
            }
            function fail(){
                alert("도서 수정이 실패했습니다.");
                location.replace("/");
            }

            httpRequest("PUT","/book",body,success,fail);
        });
    } // joinForm end





});



// HTTP GET 요청을 보내는 함수
function httpRequestGet(url,success,fail) {
    fetch(url, {
        method: "GET",
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return response.json();
        } else {
            fail();
        }
    }).then(data => {
        if (data) {
            success(data);
        }
    })
}