package com.nepplus.intent_20211102

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQ_CODE_FOR_NICKNAME = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoveToOther.setOnClickListener {

//            버튼이 눌리면 > OtherActivity로 이동하자. (화면 전환)

//            Intent로 어디서 출발 / 어디로 도착하는지 정보 설정. => 변수에 저장.
            val myIntent = Intent( this, OtherActivity::class.java)
//            출발/도착 정보를 가지고 이동
            startActivity( myIntent )

        }

        btnSandMessage.setOnClickListener {

            val inputMessage = edtMessage.text.toString()
//            메세지 화면으로 이동.

            val myIntent = Intent( this, ViewMessageActivity::class.java )
            
//            필요 데이터를 첨부하는 코드
            myIntent.putExtra("message", inputMessage)
            
            startActivity(myIntent)

        }

        btnEditNickName.setOnClickListener {

//            닉네임을 변경하기 위한 화면 이동. (Intent 3 예시)

            val myIntent = Intent( this, EddActivity::class.java )

            startActivityForResult( myIntent, REQ_CODE_FOR_NICKNAME )

        }

        btnDial.setOnClickListener {
//            안드로이드 전화화면으로 이동.

//            입력한 전화번호 저장
            val inPutPhoneNum = edtPhoneNum.text.toString()

//            제공할 정보 2가지. 1) 어떤 화면으로 갈건가? (action) / 2) 세부정보 (전화걸기 -어디로 전화_ - Uri

            val myUri = Uri.parse("tel:${inPutPhoneNum}")
            val myIntent = Intent( Intent.ACTION_DIAL, myUri )
            startActivity(myIntent)

        }

        btnCall.setOnClickListener {
            val inPutPhoneNum = edtPhoneNum.text.toString()

//            제공할 정보 2가지. 1) 어떤 화면으로 갈건가? (action) / 2) 세부정보 (전화걸기 -어디로 전화_ - Uri

            val myUri = Uri.parse("tel:${inPutPhoneNum}")
            val myIntent = Intent( Intent.ACTION_CALL, myUri )
            startActivity(myIntent)


        }

        btnSms.setOnClickListener {
            val inPutPhoneNum = edtPhoneNum.text.toString()

//            제공할 정보 2가지. 1) 어떤 화면으로 갈건가? (action) / 2) 세부정보 (전화걸기 -어디로 전화_ - Uri

            val myUri = Uri.parse("smsto:${inPutPhoneNum}")
            val myIntent = Intent( Intent.ACTION_SENDTO, myUri )

            myIntent.putExtra( "sms_body", "자동으로 입력할 문구" )

            startActivity(myIntent)
        }

        btnNaver.setOnClickListener {

            val myUri = Uri.parse("https://www.naver.com/")
            val myIntent = Intent( Intent.ACTION_VIEW, myUri )
            startActivity(myIntent)

        }

        btnKakaoStore.setOnClickListener {

            val myUri = Uri.parse("market://details?id=com.kakao.talk")
            val myIntent = Intent( Intent.ACTION_VIEW, myUri )
            startActivity(myIntent)

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        결과를 받아서 돌아오면 실행되는 함수
        Log.d("메인화면", "결과를 받아오면 무조건 실행됨")

//        구별해야할 요소 1) 어떤걸 가지러 다녀온건지? - requesCode  2) 확인/취소 구별  3) 첨부한 데이터
        Log.d("리퀘스트코드", requestCode.toString())

//        닉네임을 가지러 다녀온게 맞는가?
        if ( requestCode == REQ_CODE_FOR_NICKNAME ){

//            확인이 눌린게 맞는가?
            if ( resultCode == RESULT_OK ){

//                담아준 결과용 Intent => data에 담겨있다. => "nick" 으로 이름붙인 String을 꺼내주자.
                val newNickname = data?.getStringExtra("nick")

                txtNickname.text = newNickname

            }

        }

    }


}