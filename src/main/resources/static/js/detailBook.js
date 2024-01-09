document.addEventListener('DOMContentLoaded', function(){

    /* 도서 상세 정보를 조회합니다 */

    function successForDetail(response){
        const responseData = response.data;
        document.getElementById("bookName").innerText = responseData.bookName;
        document.getElementById("author").innerText = responseData.author;
        document.getElementById("bookImg").src = responseData.url;
        document.getElementById("status").innerText = responseData.status === 'AVAILABLE'?'대출가능':'대출불가';
        const bookDetail = document.createElement('div');
        bookDetail.innerHTML = responseData.content;
        document.getElementById("content-card").appendChild(bookDetail);

        if(responseData.status!=='AVAILABLE'){
            document.getElementById("status").classList.add('btn-secondary');
        }else{
            document.getElementById("status").classList.add('btn-warning');
        }
        let borrowForm = document.getElementById("borrow-form");

        if(responseData.status === 'AVAILABLE'){
            document.getElementById("borrowBtn").addEventListener('click',function (){
                // 대출form 표시
                borrowForm.style.display = (borrowForm.style.display === 'none')? 'block':'none';
            })
        }
    }

    function fail(){
        const container = document.getElementsByClassName("card-body");
        if (container) {
            container.innerHTML = "<p>도서 정보가 존재하지 않습니다</p>";
        }
    }

    httpRequestGet(`/detailView/${document.getElementById("book-id").value}`,successForDetail,fail)

    /* 도서 대출 관련 코드입니다 */

    let borrowForm = document.getElementById("borrow-form");
    const bookId = document.getElementById("book-id").value;
    borrowForm.addEventListener('submit',event => {
        event.preventDefault();

        body = JSON.stringify({
            "bookId" : bookId
        });

        function success(){
            alert("도서 대출이 완료되었습니다.");
            location.replace("/");
        }
        function fail(){
            alert("도서 대출이 실패했습니다.");
            location.replace("/");
        }

        httpRequest("POST","/borrow",body,success,fail);

    });


    /* 대출 이력 api 관련 코드입니다 */
    const ongoingView = document.getElementById("ongoingView");
    const recordBtn = document.getElementById("record");
    recordBtn.addEventListener('click',function(){
        // 이력 테이블 표시
        ongoingView.style.display = (ongoingView.style.display === 'none')? 'block':'none';

        /* html에 대출 이력 내용 추가 */
        function success(response){
            const responseData = response.data;
            const tbody = document.getElementById("record-tbody");

            responseData.forEach(record =>{

                const formattedBorrowTime = getFormattedTime(new Date(record.borrowTime));
                let formattedReturnTime = getFormattedTime(new Date(record.returnTime));
                if(formattedReturnTime==='1970. 01. 01. 오전 09:00:00'){
                    formattedReturnTime = '현재 대출중';
                }

                const newTr = document.createElement('tr');

                newTr.innerHTML =
                    `        <td>${record.memberName}</td>\n` +
                    `        <td>${formattedBorrowTime}</td>\n` +
                    `        <td>${formattedReturnTime}</td>\n`;
                tbody.appendChild(newTr);
            })

        } // end success

        function fail (){
            const container = document.getElementById("record-table-card");
            if (container) {
                container.innerHTML = "<p>대출 이력이 존재하지 않습니다.</p>";
            }
        }

        httpRequestGet(`/record/${document.getElementById("book-id").value}`,success,fail)

    });

});


// 시간 형식 변환
function getFormattedTime(time) {
    return new Intl.DateTimeFormat(
        'ko-KR', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        }).format(time);
}

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