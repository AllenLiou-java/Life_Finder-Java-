$(function(){
   
    $('.card-photo > img').hide();
    $('.card-desc >div').hide();

    $(window).scroll(function(){

        

        var scrollVal=$(this).scrollTop();
        // console.log(scrollVal);

        changePt=[450, 900, 1350, 1800]

        //滾輪下滑，內容顯示
        if(scrollVal>=changePt[3]){
            contentIn("#class4-ph");
            contentIn("#class4-wd");
        }else if(scrollVal>=changePt[2]){
            contentIn("#class3-ph");
            contentIn("#class3-wd");
        }else if(scrollVal>=changePt[1]){
            contentIn("#class2-ph");
            contentIn("#class2-wd");
        }else if(scrollVal>=changePt[0]){
            contentIn("#class1-ph");
            contentIn("#class1-wd");
        }

        //滾輪上滑，內容隱藏
        if(scrollVal<changePt[0]){
            contentOut("#class1-ph");
            contentOut("#class1-wd");
        }else if(scrollVal<changePt[1]){
            contentOut("#class2-ph");
            contentOut("#class2-wd");
        }else if(scrollVal<changePt[2]){
            contentOut("#class3-ph");
            contentOut("#class3-wd");
        }else if(scrollVal<changePt[3]){
            contentOut("#class4-ph");
            contentOut("#class4-wd");
        }
 
    })

    function contentIn(desc){
        $(desc).fadeIn("slow");
    }

    function contentOut(desc){
        $(desc).fadeOut("slow");
    }
   
})