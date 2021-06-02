<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>



<!-- DataTales Example -->
<div class="card shadow mb-4">
	<div class="card-body">

		<div class="card-header py-3" align="right">
			<button id="regBtn" style="color: green;">write</button>
		</div>

		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>#번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>수정일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${list }">
						<tr>
							<td><c:out value="${board.bno }" /></td>

							<td><a href="${board.bno }" class="move"> <c:out
										value="${board.title }" />
										<!-- ne : != 같지 않다. -->
										<c:if test="${board.replyCnt ne 0 }">
										<span style="color:red;">[
										<c:out value="${board.replyCnt }"/>]</span>
										</c:if>							
										
										</a></td>

							<td><c:out value="${board.writer }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<!-- 검색 상자 시작 -->
		<div>
			<div class="col-lg-12">
				<form id="searchForm" action="/board/list" method="get">
					&nbsp;&nbsp;&nbsp; <select name="type">
						<option value="" ${pageMaker.cri.type==null?"selected":"" }>
							--</option>
						<option value="T" ${pageMaker.cri.type eq "T"?"selected":"" }>
							제목</option>
						<option value="C" ${pageMaker.cri.type eq "C"?"selected":"" }>
							내용</option>
						<option value="W" ${pageMaker.cri.type eq "W"?"selected":"" }>
							작성자</option>
						<option value="TC" ${pageMaker.cri.type eq "TC"?"selected":"" }>
							제목+내용</option>
						<option value="TW" ${pageMaker.cri.type eq "TW"?"selected":"" }>
							제목+작성자</option>
						<option value="WC" ${pageMaker.cri.type eq "WC"?"selected":"" }>
							내용+작성자</option>
						<option value="TWC" ${pageMaker.cri.type eq "TWC"?"selected":"" }>
							제목+내용+작성자</option>
					</select> <input type="text" name="keyword"
						value="${pageMaker.cri.keyword }" /> <input type="hidden"
						name="pageNum" value="${pageMaker.cri.pageNum }"> <input
						type="hidden" name="amount" value="${pageMaker.cri.amount }">
					<button class="btn btn-warning">Search</button>
				</form>
			</div>
		</div>
		<!-- 검색 상자 끝 -->

	
		<!-- 쪽번호 -->
		<div>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev}">
					<li class="page-item previous"><a
						href="${pageMaker.startPage-1 }">Prev</a></li>
				</c:if>

				<c:forEach var="num" begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }">
					<li class='page-item ${pageMaker.cri.pageNum==num?"active":"" }'>
						<a href="${num }" class="page-link">${num }</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next }">
					<li class="page-item next"><a href="${pageMaker.endPage+1 }">Next</a>
					</li>
				</c:if>
			</ul>
		</div>

		<form id="actionForm" action="/board/list" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
			<input type="hidden" name="type" value="${pageMaker.cri.type }">
			<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">			
		</form>

	</div>
</div>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"></div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button class="btn btn-primary" type="button" data-dismiss="modal">close</button>
			</div>
		</div>
	</div>
</div>




<script>
	$(document).ready(
			function() {
				$('#dataTable').DataTable({
					"order" : [ [ 0, "desc" ] ], //정렬 0컬럼의 내림차순으로
					"paging" : false, // 페이징 표시 안함.
					"bFilter" : false, // 검색창 표시 안함.
					"info" : false
				// 안내창 표시 안함.
				});

				$("#regBtn").on("click", function() {
					self.location = "/board/register";
					/* 아이디 regBtn 을 클릭한다면
					현재창의 url를 쓰기로 변경 */
				});

				var result = '<c:out value="${result}"/>';
				// 자바스크립트는 형추론 이용. 
				checkModal(result);
				// 게시물 번호를 매개변수로 전달하면서 checkModal 펑션 호출

				function checkModal(result) {
					if (result === '') {
						// == 는 값만 비교, === 은 값과 형식도 비교.
						return;
					}
					if (parseInt(result) > 0) {// 전달된 문자값을 숫자화, 자바의 Integer.parseInt 비슷함.
						$(".modal-body").html(
								"게시글 " + parseInt(result) + "번이 등록");
						// 표시할 내용 만들기
					} else if (result === "success") {
						$(".modal-body").html(result);
					}
					$("#myModal").modal("show");// 모달창 표시
				}

				var actionForm = $("#actionForm");
				// 클래스 page-item 에 a 링크가 클릭 된다면,
				$(".page-item a").on(
						"click",
						function(e) {
							e.preventDefault();
							// 기본 이벤트 동작을 막고,
							console.log("click");
							// 웹 브라우저 검사 창에 클릭을 표시
							actionForm.find("input[name='pageNum']").val(
									$(this).attr("href"));
							// 액션폼 인풋태그에 페이지넘 값을 찾아서,
							// href로 받은 값으로 대체함.
							actionForm.submit();
						});

				$(".move").on(
						"click",
						function(e) {
							// 게시물 제목을 클릭한다면,
							e.preventDefault();
							// 기본 동작을 막고,
							actionForm
									.append("<input type='hidden' name='bno' "
											+ "value='" + $(this).attr("href")
											+ "'>");
							// 액션폼 요소에 bno를 추가하면서 클릭한 제목의 bno를 전달.
							actionForm.attr("action", "/board/get");
							// 기존 목록 이동 대신에 글읽기 페이지로 액션을 변경.
							// 즉, get 으로 이동하면서, bno, pageNum, pageAmount 를 전달.
							actionForm.submit();
						});

				//검색 기능 추가
				var searchForm = $("#searchForm");
				$("#searchForm button").on("click", function(e) {
					if (!searchForm.find("option:selected").val()) {
						alert("검색 종류를 선택하세요.");
						return false;
					}
					if (!searchForm.find("input[name='keyword']").val()) {
						alert("키워드를 입력하세요.");
						return false;
					}

					searchForm.find("input[name='pageNum']").val(1);
					e.preventDefault();
					searchForm.submit();

				});

			});// end_document_ready
</script>


<%@ include file="../includes/footer.jsp"%>
