package com.example.quickeng.data.local

import com.example.quickeng.model.ScriptItem

object LocalScriptsDummy {
    val scriptsByShortId: Map<String, List<ScriptItem>> = mapOf(
        "1" to listOf(
            ScriptItem(1, "HEALTH", "boost your immune system", "면역 체계를 강화하다"),
            ScriptItem(2, "FACTCHECK", "common misconception'", "흔한 오해"),
            ScriptItem(3, "SCIENCE", "clinical trials'", "임상 시험")

        ),
        "2" to listOf(
            ScriptItem(1, "ENVIRONMENT", "pose a threat", "위협이 되다"),
            ScriptItem(2, "SCIENCE", "ingest particles", "입자를 섭취하다/들이마시다"),
            ScriptItem(3, "ANALYSIS", "significant impact", "상당한 영향")

    ),
        "3" to listOf(
            ScriptItem(1, "MUSIC", "top the charts", "차트 1위를 차지하다"),
            ScriptItem(2, "TECH", "indistinguishable from humans", "인간과 구별할 수 없는"),
            ScriptItem(3, "ART", "creative process", "창작 과정"),
        ),

        "4" to listOf(
            ScriptItem(1, "ENERGY", "clean energy source", "청정 에너지원"),
            ScriptItem(2, "ECONOMY", "economic boost", "경제적 부양 효과"),
            ScriptItem(3, "SOCIETY", "community support", "지역 사회의 지지"),
        ),

        "5" to listOf(
            ScriptItem(1, "NATURE", "coral bleaching", "산호 백화 현상"),
            ScriptItem(2, "ENVIRONMENT", "restoration efforts", "복원 노력"),
            ScriptItem(3, "SCIENCE", "biodiversity", "생물 다양성"),
        ),

        "6" to listOf(
            ScriptItem(1, "TECH", "cutting-edge technology", "최첨단 기술"),
            ScriptItem(2, "REVIEW", "stand out", "눈에 띄다 / 두각을 나타내다"),
            ScriptItem(3, "BUSINESS", "game changer", "판도를 바꾸는 획기적인 것"),
        ),

        "7" to listOf(
            ScriptItem(1, "MANUFACTURING", "quality control", "품질 관리"),
            ScriptItem(2, "ROBOTICS", "autonomous robot", "자율 주행 로봇"),
            ScriptItem(3, "FUTURE", "work alongside humans", "인간과 함께 일하다"),
        ),

        "8" to listOf(
            ScriptItem(1, "CULTURE", "fine dining", "고급 정찬"),
            ScriptItem(2, "FOOD", "farm to table", "산지 직송 (농장에서 식탁까지)"),
            ScriptItem(3, "ECO", "sustainable practice", "지속 가능한 관행"),
        ),

        "9" to listOf(
            ScriptItem(1, "SECURITY", "digital signature", "디지털 서명"),
            ScriptItem(2, "IT", "quantum computing", "양자 컴퓨팅"),
            ScriptItem(3, "TECH", "encryption", "암호화"),
        ),

        "10" to listOf(
            ScriptItem(1, "SCIENCE", "computational power", "연산 능력"),
            ScriptItem(2, "LOGIC", "solve complex problems", "복잡한 문제를 해결하다"),
            ScriptItem(3, "INSIGHT", "paradigm shift", "패러다임의 전환 (인식의 대전환)"),
        ),
    )
}
