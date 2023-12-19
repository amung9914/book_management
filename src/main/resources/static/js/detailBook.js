document.addEventListener('DOMContentLoaded', function(){

    /* 도서 상세 정보를 조회합니다 */

    function successForDetail(response){
        const responseData = response.data;
        document.getElementById("bookName").innerText = responseData.bookName;
        document.getElementById("author").innerText = responseData.author;
        document.getElementById("status").innerText = responseData.status === 'AVAILABLE'?'대출가능':'대출불가';

        if(responseData.status!=='AVAILABLE'){
            document.getElementById("status").classList.remove('btn-warning');
            document.getElementById("status").classList.add('btn-secondary');
        }
    }

    function fail(){
        const container = document.getElementsByClassName("card-body");
        if (container) {
            container.innerHTML = "<p>도서 정보가 존재하지 않습니다</p>";
        }
    }

    httpRequestGet(`/detailView/${document.getElementById("book-id").value}`,successForDetail,fail)





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
                const formattedReturnTime = getFormattedTime(new Date(record.returnTime));

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