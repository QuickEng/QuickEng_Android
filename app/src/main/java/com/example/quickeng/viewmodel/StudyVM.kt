package com.example.quickeng.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quickeng.viewmodel.LearningExpression
import com.example.quickeng.ui.study.SentenceUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudyVM : ViewModel() {
    private val _videoList = listOf(
        VideoData(1, "https://www.youtube.com/watch?v=Way9Dexny3w", listOf(
            LearningExpression("It's breathtaking", "(경치가) 숨이 멎을 만큼 아름다워", "ADMIRATION"),
            LearningExpression("Dive in", "뛰어들다 / 몰입하다", "ACTION"),
            LearningExpression("Nothing fancy", "복잡할 것 없어 (단순하게 해)", "ADVICE")
        )),
        VideoData(2, "https://www.youtube.com/watch?v=pBk4NYhUOQM", listOf(
            LearningExpression("Do you guys ever think about dying?", "너희들 죽음에 대해 생각해 본 적 있어?", "SHOCKING"),
            LearningExpression("I would never want anything to change", "난 아무것도 변하지 않았으면 좋겠어", "WISH"),
            LearningExpression("Make sense", "이해가 되다 / 말이 되다", "AGREEMENT")
        )),
        VideoData(3, "https://www.youtube.com/watch?v=uYPbbksJxIg", listOf(
            LearningExpression("The world is changing", "세상은 변하고 있어", "SERIOUS"),
            LearningExpression("Theory will take you only so far", "이론만으로는 한계가 있어", "ACADEMIC"),
            LearningExpression("I don't know if we can be trusted", "우리가 신뢰받을 수 있을지 모르겠어", "DOUBT")
        )),
        VideoData(4, "https://www.youtube.com/watch?v=LEjhY15eCx0", listOf(
            LearningExpression("I am Anxiety", "난 '불안'이야 (감정 캐릭터 소개)", "GREETING"),
            LearningExpression("Make a good first impression", "좋은 첫인상을 남기다", "GOAL"),
            LearningExpression("Bottle up", "(감정을) 억누르다", "IDIOM")
        )),
        VideoData(5, "https://www.youtube.com/watch?v=_UbLZ-6scKw", listOf(
            LearningExpression("We use music to make us whole", "우리는 온전해지기 위해 음악을 이용해", "POETIC"),
            LearningExpression("I'm not alone anymore", "난 더 이상 혼자가 아니야", "RELIEF"),
            LearningExpression("Get out of here", "여기서 나가자 / 탈출하자", "URGENT")
        )),
        VideoData(6, "https://www.youtube.com/watch?v=6COmYeLsz4c", listOf(
            LearningExpression("Something is not right", "뭔가 잘못됐어", "SUSPICION"),
            LearningExpression("Don't be afraid", "두려워하지 마", "COMFORT"),
            LearningExpression("Defy gravity", "중력을 거스르다 (한계를 뛰어넘다)", "INSPIRING")
        )),
        VideoData(7, "https://www.youtube.com/watch?v=4rgIU5m68q8", listOf(
            LearningExpression("I remember that day", "난 그날을 기억해", "MEMORY"),
            LearningExpression("The arena turns men into beasts", "경기장은 사람을 짐승으로 만들지", "DRAMATIC"),
            LearningExpression("Strength and honor", "힘과 명예", "SALUTE")
        )),
        VideoData(8, "https://www.youtube.com/watch?v=73_1biulkYk", listOf(
            LearningExpression("I'm soaking wet", "나 흠뻑 젖었어 (난처한 상황 은유 가능)", "HUMOR"),
            LearningExpression("Wait for it", "잠깐 기다려봐 (곧 터질 거야)", "ANTICIPATION"),
            LearningExpression("Let's f***ing go", "자, 가보자고! (매우 강한 의지)", "SLANG")
        )),
        VideoData(9, "https://www.youtube.com/watch?v=otNh9bTj0Wg", listOf(
            LearningExpression("Quiet up", "조용히 해", "COMMAND"),
            LearningExpression("Mark my words", "내 말 명심해", "WARNING"),
            LearningExpression("Absolute favorite", "가장 좋아하는 것", "PREFERENCE")
        )),
        VideoData(10, "https://www.youtube.com/watch?v=Y6bbMQXQ180", listOf(
            LearningExpression("Push yourself", "자신을 채찍질하세요 (밀어붙이세요)", "MOTIVATION"),
            LearningExpression("Serve others", "남에게 봉사하다 / 도움을 주다", "ADVICE"),
            LearningExpression("Persistence", "끈기 / 고집", "KEYWORD")
        )),
        VideoData(11, "https://www.youtube.com/watch?v=st21dIMaGMs", listOf(
            LearningExpression("I'll have what I'm having", "난 내가 먹던 걸로 할게요 (말실수 유머)", "HUMOR"),
            LearningExpression("Continental breakfast", "유럽식 아침 식사 (간단한 조식)", "TRAVEL"),
            LearningExpression("A delight to the palate", "미각의 즐거움 (매우 맛있다)", "DINING")
        )),
        VideoData(12, "https://www.youtube.com/watch?v=naltyn53lhY", listOf(
            LearningExpression("You pissed off?", "너 화났어?", "SLANG"),
            LearningExpression("Misinterpretation", "오해 / 잘못 해석함", "EXPLANATION"),
            LearningExpression("Whatever", "그러시든지 / 마음대로 해 (무관심/짜증)", "ATTITUDE")
        )),
        VideoData(13, "https://www.youtube.com/watch?v=58bRNmxUSUg", listOf(
            LearningExpression("Number one, could you please sing", "1번 용의자, 노래 좀 불러주시겠습니까?", "REQUEST"),
            LearningExpression("It gave me chills", "소름 돋았어 (너무 좋아서)", "COMPLIMENT"),
            LearningExpression("Forgot about that part", "그 부분은 깜빡했네요", "REALIZATION")
        )),
        VideoData(14, "https://www.youtube.com/watch?v=0K4oym9Pw48", listOf(
            LearningExpression("Technically", "엄밀히 말하면", "LOGIC"),
            LearningExpression("Gain traction", "추진력을 얻다 / 인기를 얻다", "BUSINESS"),
            LearningExpression("Hardcore", "빡센 / 강렬한", "SLANG")
        )),
        VideoData(15, "https://www.youtube.com/watch?v=9Vk4jL5Z9y8", listOf(
            LearningExpression("Joey doesn't share food", "조이는 음식을 나눠 먹지 않아!", "ASSERTION"),
            LearningExpression("It's not a big deal", "별일 아니잖아", "EXCUSE"),
            LearningExpression("Tiny portion", "아주 적은 양", "COMPLAINT")
        )),
        VideoData(16, "https://www.youtube.com/watch?v=pIpmITBocfM", listOf(
            LearningExpression("Anecdotal evidence", "일화적인 증거 (과학적이지 않은 개인 경험)", "DEBATE"),
            LearningExpression("I beg to differ", "제 생각은 다릅니다 (정중한 반대)", "FORMAL"),
            LearningExpression("What are the odds?", "이럴 확률이 얼마나 될까? (우연이다)", "SURPRISE")
        )),
        VideoData(17, "https://www.youtube.com/watch?v=B6uuIHpFkuo", listOf(
            LearningExpression("Just one of the guys", "남자들 무리에 잘 섞이는 사람", "SOCIAL"),
            LearningExpression("Too soft", "너무 무르다 / 약하다", "CRITICISM"),
            LearningExpression("Make room", "자리를 만들어 주다", "KINDNESS")
        )),
        VideoData(18, "https://www.youtube.com/watch?v=1HAGuju_yKY", listOf(
            LearningExpression("Why can't you just be normal?", "넌 왜 그냥 평범할 수 없는 거니?", "FRUSTRATION"),
            LearningExpression("Keep it down", "조용히 해 / 소리 낮춰", "WARNING"),
            LearningExpression("Different is good", "다른 건 좋은 거야 (문맥상 암시)", "LESSON")
        )),
        VideoData(19, "https://www.youtube.com/watch?v=l_xEjk-0-48", listOf(
            LearningExpression("Playing the orchestra", "오케스트라를 지휘하다 (팀을 조율하다)", "METAPHOR"),
            LearningExpression("You're not a musician", "당신은 연주자가 아니잖아", "CONFRONTATION"),
            LearningExpression("System error", "시스템 오류", "TECHNICAL")
        )),
        VideoData(20, "https://www.youtube.com/watch?v=IlXwTxpExn0", listOf(
            LearningExpression("You are obsession", "넌 강박적이야", "ACCUSATION"),
            LearningExpression("Let me remain clear", "분명히 말해두겠는데", "FIRM"),
            LearningExpression("Dating you is like dating a stairmaster", "너랑 사귀는 건 헬스 기구랑 사귀는 것 같아 (너무 힘들어)", "INSULT")
        )),
        VideoData(21, "https://www.youtube.com/watch?v=Ja2fgquYTCg", listOf(
            LearningExpression("You take yourself too seriously", "넌 너 자신을 너무 대단하게 생각하는구나", "SARCASM"),
            LearningExpression("Pile of stuff", "잡동사니 더미", "DISMISSAL"),
            LearningExpression("Filtered down", "(유행이) 차례로 내려오다", "FASHION")
        )),
        VideoData(22, "https://www.youtube.com/watch?v=SL_YMm9C6tw", listOf(
            LearningExpression("Here's to the ones who dream", "꿈꾸는 이들을 위하여 (건배사)", "TOAST"),
            LearningExpression("A bit of madness", "약간의 광기", "POETIC"),
            LearningExpression("Mess it up", "망치다", "REGRET")
        )),
        VideoData(23, "https://www.youtube.com/watch?v=xDAsABdkWSc", listOf(
            LearningExpression("Not my tempo", "내 템포가 아니야", "ANGER"),
            LearningExpression("Rushing or dragging", "빨라지거나 느려지거나 (박자가 안 맞음)", "MUSIC"),
            LearningExpression("Start over", "처음부터 다시 해", "COMMAND")
        )),
        VideoData(24, "https://www.youtube.com/watch?v=xTShxR1kIqU", listOf(
            LearningExpression("I'm not done", "난 아직 안 끝났어 (죽을 수 없어)", "DENIAL"),
            LearningExpression("The Great Beyond", "저세상 / 사후세계", "MYSTERY"),
            LearningExpression("Check out", "확인하다 / (죽어서) 떠나다", "SLANG")
        )),
        VideoData(25, "https://www.youtube.com/watch?v=bTEGctZE9Wk", listOf(
            LearningExpression("Run a plate", "(차량) 번호판을 조회하다", "POLICE"),
            LearningExpression("Hang in there", "조금만 참아", "PATIENCE"),
            LearningExpression("What do you call...", "...를 뭐라고 부르지?", "QUESTION")
        )),
        VideoData(26, "https://www.youtube.com/watch?v=d8oaFdtm0DQ", listOf(
            LearningExpression("I'm trash", "난 쓰레기야", "IDENTITY"),
            LearningExpression("Made for", "...를 위해 만들어진", "PURPOSE"),
            LearningExpression("Stop it", "그만해", "COMMAND")
        )),
        VideoData(27, "https://www.youtube.com/watch?v=rnkBha4274k", listOf(
            LearningExpression("In summer", "여름에는", "DREAM"),
            LearningExpression("Let off steam", "열을 식히다 / 스트레스를 풀다", "IDIOM"),
            LearningExpression("Solid water", "고체 물 (얼음)", "FUNNY")
        )),
        VideoData(28, "https://www.youtube.com/watch?v=zAFcV7zuUDA", listOf(
            LearningExpression("strong form of the knot", "매듭의 강력한 형태 (잘 풀리지 않는 매듭)", "INSTRUCTION"),
            LearningExpression("pull the strands", "끈의 가닥을 당기다", "ACTION"),
            LearningExpression("let me show you", "제가 보여드리겠습니다", "DEMONSTRATION")
        )),
        VideoData(29, "https://www.youtube.com/watch?v=0Yd3Z36Y8ps", listOf(
            LearningExpression("multitasking is a myth", "멀티태스킹은 허구(신화)입니다", "OPINION"),
            LearningExpression("pay attention", "주의를 기울이다 / 집중하다", "ADVICE"),
            LearningExpression("shrink your world", "당신의 세상을 좁히다 (한 가지에 집중하다)", "METAPHOR")
        )),
        VideoData(30, "https://www.youtube.com/watch?v=QA8q9Y4o9Yc", listOf(
            LearningExpression("future self", "미래의 나 자신", "PSYCHOLOGY"),
            LearningExpression("commit to saving", "저축하기로 굳게 다짐하다", "FINANCE"),
            LearningExpression("pre-commitment", "사전 확약 (미리 결정해두는 것)", "STRATEGY")
        )),
        VideoData(31, "https://www.youtube.com/watch?v=FRGd7qV4cLU", listOf(
            LearningExpression("breaking records", "기록을 경신하다", "SPORTS"),
            LearningExpression("push limits", "한계를 밀어붙이다 (초월하다)", "MOTIVATION"),
            LearningExpression("it feels natural", "자연스럽게 느껴진다", "FEELING")
        )),
        VideoData(32, "https://www.youtube.com/watch?v=rxiiqeQY2aI", listOf(
            LearningExpression("sense of smell", "후각", "SCIENCE"),
            LearningExpression("detect aromas", "향기를 감지하다", "EXPLANATION"),
            LearningExpression("combine to create flavor", "결합하여 맛을 만들어내다", "SCIENCE")
        )),
        VideoData(33, "https://www.youtube.com/watch?v=yP5_gA0RddM", listOf(
            LearningExpression("ecosystem restoration", "생태계 복원", "ENVIRONMENT"),
            LearningExpression("scale up", "규모를 확대하다 / 키우다", "BUSINESS"),
            LearningExpression("plant trees", "나무를 심다", "ACTION")
        )),
        VideoData(34, "https://www.youtube.com/watch?v=t0kACis_dJE", listOf(
            LearningExpression("regularity is king", "규칙적인 것이 가장(왕)이다", "ADVICE"),
            LearningExpression("drop your core body temperature", "심부 체온을 낮추다", "HEALTH"),
            LearningExpression("stay in bed", "침대에 머물다 (깨어있는데 누워있다)", "INSTRUCTION")
        )),
        VideoData(35, "https://www.youtube.com/watch?v=q6b9D55hXKs", listOf(
            LearningExpression("probability of precipitation", "강수 확률", "WEATHER"),
            LearningExpression("misinterpretation", "오해 / 잘못된 해석", "EXPLANATION"),
            LearningExpression("forecast accuracy", "일기 예보 정확도", "SCIENCE")
        )),
        VideoData(36, "https://www.youtube.com/watch?v=rXjO5l9J8_Q", listOf(
            LearningExpression("extraordinary ability", "비범한(놀라운) 능력", "DESCRIPTION"),
            LearningExpression("born with", "~을 가지고 태어난 (선천적인)", "BIOGRAPHY"),
            LearningExpression("see the world differently", "세상을 다르게 바라보다", "PERSPECTIVE")
        )),
        VideoData(37, "https://www.youtube.com/watch?v=JYjg7I0l0wU", listOf(
            LearningExpression("stuck in a rut", "타성에 젖다 / 틀에 박힌 생활을 하다", "IDIOM"),
            LearningExpression("give it a shot", "한번 시도해보다", "SUGGESTION"),
            LearningExpression("sustainable changes", "지속 가능한 변화", "LIFESTYLE")
        ))
    )

    fun getVideoData(id: Int): VideoData? {
        return _videoList.find { it.id == id }
    }

    private val _sentences = MutableStateFlow<List<SentenceUi>>(emptyList())
    val sentences = _sentences.asStateFlow()

    fun addSentences(newItems: List<SentenceUi>) {
        _sentences.value = _sentences.value + newItems
    }

    fun deleteSentence(id: String) {
        _sentences.value = _sentences.value.filter { it.id != id }
    }
}