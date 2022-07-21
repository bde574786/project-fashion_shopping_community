<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

	<div class="community-container">

	    <c:forEach var="communityBoard" items="${communityBoardList.content}">
	    <input id="pageSize" type="hidden" value="${communityBoardList.pageable.pageSize}">
	    
	        <div class="commu-social-container">
	
	            <input id="userId" type="hidden" value="${principal.user}" />
	            <input id="communityBoardId" type="hidden" value="${communityBoard.id}" />
	            
	            <div class="commu-container">
	            	<div style="height: 20px"></div>
	                <div class="commu-social-img-box">
	                    <img class="commu-social-img" alt="" src="/image/model1.jpg">
	                </div>
	
	                <div class="commu-social-main-container">
	                    <div id="commu-icon-box-${communityBoard.id}">
	                        <div class="commu-like-icon-box" onclick="commu.communityLike(${communityBoard.id}, ${communityBoard.likeCount})">
	                            <c:set var="myLike" value="0"></c:set>
	                            <c:forEach var="like" items="${likeList}">
	                                <c:if test="${like.board.id == communityBoard.id}">
	                                    <c:set var="myLike" value="ok"></c:set>
	                                </c:if>
	                            </c:forEach>
	
	                            <c:choose>
	                                <c:when test="${myLike == 'ok'}">
	                                    <i class="fa-solid fa-heart fa-lg" style="color: rgb(240, 81, 115)"></i>
	                                </c:when>
	                                <c:otherwise>
	                                    <i style="color: black" id="before-like" class="fa-regular fa-heart fa-lg"></i>
	                                </c:otherwise>
	                            </c:choose>
	                            <span id="likeCount-${communityBoard.id}"
	                            class="commu-social-span-goodlook-count commu-text">${communityBoard.likeCount}</span>
	                        </div>
	                        
	                    </div>
	
	                    <hr class="commu-social-hr-goodlook-line" />
	
	                    <div style="height: 10px"></div>
	
	                    <div>
	                        <div class="commu-social-div-title-container">
	                            <h4 class="commu-text commu-social-title">${communityBoard.title}</h4>
	                        </div>
	                        <div style="height: 10px"></div>
	                        <div class="commu-social-div-title-container">
	                            <span class="commu-social-p-username commu-text">${communityBoard.user.username}</span>
	                        </div>
	                    </div>
	
	                    <div style="height: 40px"></div>
	
	                    <div>
	                        <div class="div-content-container">
	                            <span class="commu-social-span-content commu-text">${communityBoard.content}</span>
	                        </div>
	                    </div>
	
	                    <div style="height: 50px"></div>
	
	                    <div>
	                        <div class="commu-reply-firstline">
	                            <i class="fa-regular fa-pen-to-square"></i>
	                            <button type="button" class="btn-up commu-btn" id="commu-btn-insert-${communityBoard.id}" onclick="commu.insertReply(${communityBoard.id}, ${principal.user.id})">up</button>
	                        </div>
	
	                        <div>
	                            <input type="text" placeholder="한 마디" class="commu-social-input-reply commu-social-input"
	                                id="commu-input-reply-${communityBoard.id}" />
	                        </div>
	                    </div>
	                </div>
	                <div style="height: 150px"></div>
	            </div>
	        </div>
	    </c:forEach>
    
    </div>

	<p id="result"></p>
	<input id="pageNumber" type="hidden" value="${communityBoardList.number}">

    <script>history.scrollRestoration = "manual"</script>
    <script src="/js/commu.js"></script>
    <script src="/js/index.js"></script>
    
</body>

</html>