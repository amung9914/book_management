// 대출 내역을 불러옵니다
httpRequestGet("/borrowList",success,fail);

function fail(){
    const container = document.getElementById("root-container");
    if (container) {
        container.innerHTML = "<h1>현재 대출중인 도서가 없습니다</h1>";
    }
}

function success(response){
    const responseData = response.data;

    const totalCount = document.createElement('p');
    totalCount.textContent = `현재 대출 중인 도서 수는 ${response.count} 건입니다.`;
    totalCount.id = 'total-count';

    const rootContainer = document.getElementById("root-container");

    const container = document.createElement('div');
    container.classList.add('container');

    responseData.forEach(book => {
        const row = document.createElement('div');
        row.classList.add('row-6');

        // 카드 생성
        const card = document.createElement('div');
        card.classList.add('card');

        // 카드 바디 생성
        const cardBody = document.createElement('div');
        cardBody.classList.add('card-body');

        // 책 제목 표시
        const title = document.createElement('h5');
        title.classList.add('card-title');
        title.id = 'bookName';
        title.textContent = book.bookName;

        // 작가 정보 표시
        const author = document.createElement('p');
        author.classList.add('card-text');
        author.id = 'author';
        author.textContent = `저자: ${book.author}`;

        // '반납하기' 링크 생성
        bookId = book.bookId;
        const link = document.createElement('a');
        link.classList.add('btn', 'btn-warning');
        link.textContent = '반납하기';

        link.addEventListener('click',function(){
            body = JSON.stringify({
                "bookId":bookId
            });

            function  success(){
                alert("도서 반납이 완료되었습니다");
                location.replace("/returnForm");
            }
            function fail(){
                alert("도서 반납이 실패하였습니다");
                location.replace("/returnForm");
            }

            httpRequest("PUT","borrow",body,success,fail);
        })


        // 요소를 추가합니다
        cardBody.appendChild(title);
        cardBody.appendChild(author);
        cardBody.appendChild(link);
        card.appendChild(cardBody);
        row.appendChild(card);

        container.appendChild(row);

    }); // end for

    rootContainer.appendChild(totalCount);
    rootContainer.appendChild(container);

}

document.addEventListener('DOMContentLoaded', function() {
    const returnForm = document.getElementById("return-form");
    if (returnForm) {
        returnForm.addEventListener('submit', event => {
            event.preventDefault();
            const memberName = document.getElementById("memberName").value;

            function add(response) {
                const rootContainer = document.getElementById("root-container");
                rootContainer.innerText = "";
                const semiCount = document.createElement('h4');
                semiCount.textContent = memberName + "님의 대출중인 도서 목록입니다";
                semiCount.id = 'semi-count';
                rootContainer.appendChild(semiCount);
                return successForMember(response);
            }

            function fail() {
                alert(memberName + "님의 대출 내역이 존재하지 않습니다");
                location.replace("returnForm");
            }

            httpRequestGet(`/borrowList/${memberName}`, add, fail);


        });
    }
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


/**
 *  특정 멤버의 대출 리스트
 */
function successForMember(response){
    const responseData = response.data;

    let rootContainer = document.getElementById("root-container");

    let container = document.createElement('div');
    container.classList.add('container');
    container.id = 'total-container';

    responseData.forEach(book => {
        const row = document.createElement('div');
        row.classList.add('row-6');

        // 카드 생성
        const card = document.createElement('div');
        card.classList.add('card');

        // 카드 바디 생성
        const cardBody = document.createElement('div');
        cardBody.classList.add('card-body');

        // 책 제목 표시
        const title = document.createElement('h5');
        title.classList.add('card-title');
        title.id = 'bookName';
        title.textContent = book.bookName;

        // 작가 정보 표시
        const author = document.createElement('p');
        author.classList.add('card-text');
        author.id = 'author';
        author.textContent = `저자: ${book.author}`;

        // '반납하기' 링크 생성
        bookId = book.bookId;
        const link = document.createElement('a');
        link.classList.add('btn', 'btn-warning');
        link.textContent = '반납하기';

        link.addEventListener('click',function(){
            body = JSON.stringify({
                "bookId":bookId
            });

            function  success(){
                alert("도서 반납이 완료되었습니다");
                location.replace("/returnForm");
            }
            function fail(){
                alert("도서 반납이 실패하였습니다");
                location.replace("/returnForm");
            }

            httpRequest("PUT","borrow",body,success,fail);
        })


        // 요소를 추가합니다
        cardBody.appendChild(title);
        cardBody.appendChild(author);
        cardBody.appendChild(link);
        card.appendChild(cardBody);
        row.appendChild(card);

        container.appendChild(row);

    }); // end for

    rootContainer.appendChild(container);

}