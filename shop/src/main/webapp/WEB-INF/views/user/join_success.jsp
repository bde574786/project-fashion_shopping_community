<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="user-header-line"><h2 class="user-header-text">C o n g r a t u l a t i o n</h2></div>
<div style="height: 60px;"></div>

<div class="user-success-content-box">
	<h3>유신사의 회원이 되신걸 축하드립니다.</h3>
</div>

<div style="height: 60px;"></div>
	
<div class="join-sucssess-container">

    <div class="user-input-container">
        <label class="user-input-label">
            <span class="label-txt">YOUR ID</span>
            <input value="asd1" type="text" class="input" name="username" id="username" readonly>
            <div class="line-box">
                <div class="line"></div>
            </div>
        </label>

        <div class="user-empty-box"></div>
        <div class="user-empty-box"></div>
        <div class="user-empty-box"></div>

        <div class="user-input-container">
            <div>
                <label class="user-input-label">
                    <span class="label-txt">YOUR NAME</span>
                    <input value="홍길동" type="text" class="input" name="name" readonly>
                    <div class="line-box">
                        <div class="line"></div>
                    </div>
                </label>
			</div>
		</div>
		
		<div class="user-input-container">
            <div>
                <label class="user-input-label">
                    <span class="label-txt">YOUR EMAIL</span>
                    <input value="hyunah@google.com" type="email" class="input" name="email" readonly>
                    <div class="line-box">
                        <div class="line"></div>
                    </div>
                </label>
            </div>
        </div>

        <div class="user-empty-box"></div>

        <div class="user-input-container">
            <div>
                <label class="user-input-label">
                    <span class="label-txt">YOUR ADDRESS</span>
                    <input value="부산시 진구" type="text" class="input" name="address" readonly>
                    <div class="line-box">
                        <div class="line"></div>
                    </div>
                </label>
			</div>
		</div>
		
        <div class="user-input-container">
            <div>		
                <label class="user-input-label">
                    <span class="label-txt">YOUR PHONE</span>
                    <input value="01012345432" type="number" class="input" name="phoneNumber" readonly>
                    <div class="line-box">
                        <div class="line"></div>
                    </div>
                </label>
            </div>
        </div>

        <div class="user-empty-box"></div>
        <div class="user-empty-box"></div>
        <div class="user-empty-box"></div>

		<div class="user-join-success-btn-login-box">
			<button type="button" id="btn-login-page" class="user-btn"><a class="user-success-btn-a" href="/security/login_form">로그인</a></button>
		</div>
        
    </div>
</div>
</body>

</html>