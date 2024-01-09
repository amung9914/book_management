
    fetch("/bookList", {
        method: "GET",
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            /* http응답 본문을 JSON 형식으로 해석 */
            return response.json();
        } else {
            const container = document.getElementById("root-container");
            if (container) {
                container.innerHTML = "<h1>요청이 정상적으로 처리되지 않았습니다.</h1>";
            }
            throw new Error("서버 응답 오류: "+ response.status);
        }
    }).then(data => {
       if(data) {
           success(data);
       }
    }).catch(error => {
        console.log("오류발생:",error);
    });

function success(response){
    const responseData = response.data;

    const totalCount = document.createElement('p');
    totalCount.textContent = `전체 도서 수는 ${response.count} 건입니다.`;
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

        // '보러 가기' 링크 생성
        bookId = book.bookId;
        const link = document.createElement('a');
        link.classList.add('btn', 'btn-warning');
        link.textContent = '보러 가기';
        link.href = "detailBook/"+bookId;

        // 각각의 요소를 구조에 추가
        cardBody.appendChild(title);
        cardBody.appendChild(author);
        cardBody.appendChild(link);
        card.appendChild(cardBody);
        row.appendChild(card);
        // 컨테이너에 추가
        container.appendChild(row);

    });

    rootContainer.appendChild(totalCount);
    rootContainer.appendChild(container);

}




