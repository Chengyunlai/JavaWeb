$(function () {
    $("#username").focus();
    $("#username").attr("placeholder", "");
    $("#userTips").fadeIn(1000);

    //当用户框获取焦点的时候，将文本框的placeholder内容清空
    $("#username").focus(function () {
        // $("#userTips").css("visibility","visible");
        console.log("用户名框获得焦点");
        $("#username").attr("placeholder", "");
    })

    //当用户框失去焦点的时候，将用户框中的placeholder内容恢复
    $("#username").focusout(function () {
        // $("#userTips").css("visibility","hidden");
        // $("#userTips").fadeOut(400);
        console.log("用户名框失去焦点");
        $("#username").attr("placeholder", "请输入用户名");
    })

    //当密码框获取焦点的时候，将密码框的placeholder内容清空
    $("#password").focus(function () {
        // $("#passTips").css("visibility","visible");
        $("#passTips").fadeIn(400);
        console.log("密码框获得焦点");
        $("#password").attr("placeholder", "");
    })

    //当密码框失去焦点的时候，将密码框的placeholder内容恢复
    $("#password").focusout(function () {
        // $("#passTips").css("visibility","hidden");
        // $("#userTips").fadeOut(400);
        console.log("密码框失去焦点");
        $("#password").attr("placeholder", "请输入密码");
    })

    //当用户框的内容被改变的时候，去检查用户名的内容是否合规
    $("#username").keyup(function () {
        var user = $("#username").val();
        var patt_user = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
        if (patt_user.test(user)) {
            $("#username_prompt").css("color", "#10ac84");
            console.log("合法");

        } else {
            console.log("非法");
            $("#username_prompt").css("color", "red");
        }
    })

    $("#password").keyup(function () {
        var patt_pass = /^[\w]{6,16}$/;
        var pass = $("#password").val();
        if (patt_pass.test(pass)) {
            $("#password_prompt").css("color", "#10ac84");
            console.log("合法");

        } else {
            console.log("非法");
            $("#password_prompt").css("color", "red");
        }
    })

    //
    $("#subm").click(function () {
        // alert("提交被点击");
        var pass = $("#password").val();
        var user = $("#username").val();

        var patt_user = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
        var patt_pass = /^[\w]{6,16}$/;

        if ((patt_pass.test(pass) & patt_user.test(user))) {

            $("#subm").attr("type", "submit");
        } else {
            $("#subm").attr("type", "button");
            $("#subm").attr("required", "required");
            $("#password_prompt").css("color", "red");
            $("#username_prompt").css("color", "red");
        }
    })

    $("#rese").click(function () {
        // alert("重置被点击");
        $("#password_prompt").css("color", "white");
        $("#username_prompt").css("color", "white");
        $("#username").reset();
        $("#password").reset();

    })

})