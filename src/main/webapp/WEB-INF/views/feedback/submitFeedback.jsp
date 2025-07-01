<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Submit Feedback</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            background-color: #e9ecef;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 20px;
            max-width: 700px;
            background-color: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }

        h1 {
            text-align: center;
            margin-bottom: 40px;
            color: #343a40;
            font-size: 2.5rem;
        }

        .form-group {
            margin-bottom: 25px;
        }

        label {
            font-weight: 600;
            color: #495057;
            display: block;
            margin-bottom: 5px;
        }

        .form-control {
            border-radius: 8px;
            border: 1px solid ;

            padding: 10px;
            font-size: 1rem;
        }

        textarea.form-control {
            resize: vertical;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
            padding: 12px 25px;
            border-radius: 8px;
            font-size: 1.1rem;
            transition: background-color 0.2s ease-in-out, border-color 0.2s ease-in-out;
            margin-bottom: 30px
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .btn-default {
            background-color: #6c757d;
            border-color: #6c757d;
            color: #fff;
            padding: 12px 25px;
            border-radius: 8px;
            font-size: 1.1rem;
            transition: background-color 0.2s ease-in-out, border-color 0.2s ease-in-out;
        }

        .btn-default:hover {
            background-color: #545b62;
            border-color: #545b62;
        }

        .button-group {
            margin-top: 30px;
            text-align: center;
            margin-bottom: 41px;

        }

        .button-group .btn {
            margin: 0 15px;
        }

        /* Vertical Radio Buttons */
        .vertical-radio-group {
            list-style: none;
            padding-left: 0;
            margin: 0;
        }

        .vertical-radio-group li {
            margin-bottom: 8px;
            list-style: none;

        }

        .vertical-radio-group label {
            display: inline-block;
            cursor: pointer;
            background-color:#c2e2f5;
        }

        .vertical-radio-group input[type="radio"] {
            margin-right: 5px;
            vertical-align: middle;
        }

        .vertical-radio-group label {
            display: flex;
            align-items: center;
            padding: 8px 12px;
            border: 1px solid #ced4da;
            border-radius: 6px;
            background-color: #f8f9fa;
            transition: background-color 0.2s ease-in-out, border-color 0.2s ease-in-out;
            font-weight: normal;

        }

        .vertical-radio-group label:hover {
            background-color: #e2e6ea;
            border-color: #adb5bd;
        }


        .vertical-radio-group input[type="radio"]:checked + label {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }

        .vertical-radio-group input[type="radio"] {
            appearance: none;
            width: 16px;
            height: 16px;
            border: 1px solid #ced4da;
            border-radius: 50%;
            outline: none;
            margin-right: 8px;
            vertical-align: middle;
            position: relative;
            top: -1px;
            background-color: white;
        }

        .vertical-radio-group input[type="radio"]:checked {
            border-color: #007bff;
            background-color: white;
        }


        .vertical-radio-group input[type="radio"]:checked::before {
            content: '';
            display: block;
            width: 8px;
            height: 8px;
            background-color: #007bff;
            border-radius: 50%;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .vertical-radio-group input[type="radio"]:focus + label {
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }

        /* Category Select Styling */
        .form-group select.form-control {
            appearance: none;
            background-image: url('data:image/svg+xml;charset=UTF-8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="%23343a40"><path d="M7 10l5 5 5-5z"/></svg>');
            background-repeat: no-repeat;
            background-position: right 10px top 50%;
            background-size: 16px;
            padding-right: 30px;
            font-size: 13px;
            height: 42px;
        }
        .vertical-radio-group li label{
        background-color: #E4F6F8;
        }
    </style>
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h1>Submit Feedback</h1>
        <form:form action="submit" method="post" modelAttribute="feedbackCommand">
            <div class="form-group">
                <form:label path="categoryId">Category:</form:label>
                <form:select path="categoryId" class="form-control">
                    <form:option value="1">Amazon</form:option>
                    <form:option value="2">Flipkart</form:option>
                    <form:option value="3">eBay</form:option>
                    <form:option value="4">Alibaba</form:option>
                    <form:option value="5">Walmart</form:option>
                    <form:option value="6">Shopify</form:option>
                    <form:option value="7">ASOS</form:option>
                    <form:option value="8">Shein</form:option>
                    <form:option value="9">Nike</form:option>
                    <form:option value="10">Adidas</form:option>
                    <form:option value="11">Blinkit</form:option>

                </form:select>
            </div>

            <div class="form-group">
                <label>Question 1: How satisfied are you with our Service?</label>
                <ul class="vertical-radio-group">
                    <li><label><form:radiobutton path="question1Answer" value="1" id="q1_1"/>Very satisfied</label></li>
                    <li><label><form:radiobutton path="question1Answer" value="2" id="q1_2"/>Satisfied</label></li>
                    <li><label><form:radiobutton path="question1Answer" value="3" id="q1_3"/>Neutral</label></li>
                    <li><label><form:radiobutton path="question1Answer" value="4" id="q1_4"/>UnSatisfied</label></li>
                    <li><label><form:radiobutton path="question1Answer" value="5" id="q1_5"/>Very Dissatisfied</label></li>
                </ul>
            </div>

            <div class="form-group">
                <label>Question 2: How likely are you to recommend our service to others?</label>
                <ul class="vertical-radio-group">
                    <li><label><form:radiobutton path="question2Answer" value="1" id="q2_1" />Very Likely</label></li>
                    <li><label><form:radiobutton path="question2Answer" value="2" id="q2_2" />Likely</label></li>
                    <li><label><form:radiobutton path="question2Answer" value="3" id="q2_3" />Neutral</label></li>
                    <li><label><form:radiobutton path="question2Answer" value="4" id="q2_4" />Unlikely</label></li>
                    <li><label><form:radiobutton path="question2Answer" value="5" id="q2_5" />Very Unlikely</label></li>
                </ul>
            </div>
               <div class="form-group">
                <label>Question 3: How would you rate the overall quality of our service? </label>
                <ul class="vertical-radio-group">
                    <li><label><form:radiobutton path="question3Answer" value="1" id="q3_1" />Excellent</label></li>
                    <li><label><form:radiobutton path="question3Answer" value="2" id="q3_2" />Good</label></li>
                    <li><label><form:radiobutton path="question3Answer" value="3" id="q3_3" />Average</label></li>
                    <li><label><form:radiobutton path="question3Answer" value="4" id="q3_4" />Poor</label></li>
                    <li><label><form:radiobutton path="question3Answer" value="5" id="q3_5" />Very Poor</label></li>
                </ul>
            </div>

            <div class="form-group">
                <label>Question 4: Was the service easy to use?</label>
                <ul class="vertical-radio-group">
                    <li><label><form:radiobutton path="question4Answer" value="1" id="q4_1" />Very Easy</label></li>
                    <li><label><form:radiobutton path="question4Answer" value="2" id="q4_2" />Easy</label></li>
                    <li><label><form:radiobutton path="question4Answer" value="3" id="q4_3" />Neutral</label></li>
                    <li><label><form:radiobutton path="question4Answer" value="4" id="q4_4" />Difficult</label></li>
                    <li><label><form:radiobutton path="question4Answer" value="5" id="q4_5" />Very Difficult</label></li>
                </ul>

            </div>
             <div class="form-group">
                <label>Question 5: Did our service meet your expectations?</label>
                 <ul class="vertical-radio-group">
                    <li><label><form:radiobutton path="question5Answer" value="1" id="q5_1" />Exceeded Expectations</label></li>
                    <li><label><form:radiobutton path="question5Answer" value="2" id="q5_2" />Met Expectations</label></li>
                    <li><label><form:radiobutton path="question5Answer" value="3" id="q5_3" />Neutral</label></li>
                    <li><label><form:radiobutton path="question5Answer" value="4" id="q5_4" />Fell short of Expectations</label></li>
                    <li><label><form:radiobutton path="question5Answer" value="5" id="q5_5" />Did not meet Expectations</label></li>
                </ul>
            </div>

            <div class="form-group">
                <label>Question 6: How likely are you to use our service again?</label>
                <ul class="vertical-radio-group">
                    <li><label><form:radiobutton path="question6Answer" value="1" id="q6_1" />Very Likely</label></li>
                    <li><label><form:radiobutton path="question6Answer" value="2" id="q6_2" />Likely</label></li>
                    <li><label><form:radiobutton path="question6Answer" value="3" id="q6_3" />Neutral</label></li>
                    <li><label><form:radiobutton path="question6Answer" value="4" id="q6_4" />Unlikely</label></li>
                    <li><label><form:radiobutton path="question6Answer" value="5" id="q6_5" />Very Unlikely</label></li>
                </ul>

            </div>
             <div class="form-group">
                <label>Question 7: Would you like to provide any additional suggestions? </label>
                 <ul class="vertical-radio-group">
                    <li><label><form:radiobutton path="question7Answer" value="1" id="q7_1" />Yes</label></li>
                    <li><label><form:radiobutton path="question7Answer" value="0" id="q7_0" />No</label></li>
                </ul>

            </div>

            <div class="button-group">
              <button type="submit" class="btn btn-primary">Submit</button>
              <a href="<c:url value="/userDashboard" />" class="btn btn-default">Cancel</a>
            </div>
        </form:form>
    </div>

<jsp:include page="../footer.jsp" />
</body>
</html>