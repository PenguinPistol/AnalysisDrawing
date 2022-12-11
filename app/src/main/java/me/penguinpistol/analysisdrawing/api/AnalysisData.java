package me.penguinpistol.analysisdrawing.api;


import kotlin.Triple;

public class AnalysisData {
    /** 중정길이 */
    public float middleFaceLength;
    /** 하정길이 */
    public float lowerFaceLength;
    /** 입술각도 left, right */
    public Pair<Float> lipAngles;
    /** 황금삼각비 각도 a, b, c */
    public Triple<Float, Float, Float> goldenTriangle;
    /** 눈과 입 평행각도차 */
    public float eyeMouthParallelAngle;
    /** 가로 비율 */
    public float faceHorizontalRatio;
    /** 턱의 면적 left, right */
    public Pair<Float> jawAreas;
    /** 얼굴 형 */
    public String faceShape;
    /** 눈 각도 left, right */
    public Pair<Float> eyeAngles;
    /** 눈 높이 left, right */
    public Pair<Float> eyeHeights;
    /** 눈 너비 left, right */
    public Pair<Float> eyeWidths;
    /** 눈 간격 */
    public float eyeDistance;
    /** 눈동자 비율 left, right */
    public Pair<String> eyeRatio;
    /** 미안거간거 left, right */
    public Pair<Float> miangeogangeo;
    /** 쌍꺼풀 유무 left, right */
    public Pair<String> doubleEyeLids;
    /** 안검하수비율 left, right */
    public Pair<String> ptosisRatio;
    /** 눈과 눈썹거리 left, right */
    public Pair<Float> eyeAndEyebrowDistance;
    /** 눈썹 길이 left, right*/
    public Pair<Float> eyebrowLength;
    /** 눈썹 높이 */
    public Pair<Float> eyebrowHeight;
    /** 눈썹 형태 */
    public String eyebrowShape;
    /** 눈높이와 눈썹높이 비율 */
    public String eyeAndEyebrowHeightRatio;
    /** 눈썹 간격 */
    public float eyebrowDistance;
    /** 코 길이 */
    public float noseLength;
    /** 코 너비 */
    public float noseWidth;
    /** 입술 두께 upper, lower */
    public Pair<Float> lipThickness;
    /** 입꼬리 각도 */
    public Pair<Float> lipTailAngle;
    /** 입술 비율 */
    public String lipRatio;
    /** 입술산 각도 */
    public float lipMountainAngle;
    /** 입술 너비 */
    public float lipWidth;
    /** 인중 길이 */
    public float philtrumLength;
    /** 턱 길이 */
    public float jawLength;
    /** 인중 턱 길이비율 */
    public String philtrumAndJawRatio;
    /** 관골 너비 */
    public float zygomaWidth;
    /** 하악 너비 */
    public float mandibleWidth;
    /** 관골 하악 너비비율 */
    public String zygomaAndMandibleRatio;
    /** 턱 코 길이비율 */
    public String jawAndNoseRatio;
    /** 앞 턱 길이 */
    public float frontChinLength;
    /** 볼의 볼륨감 유무 */
    public String frontCheek;


    /*
    // face ratio
        // 중정길이
        lab_face_ratio.data.details.common.upperAndLowerFaceLength.upperAndLowerFaceRatioMiddleLength
        // 하정길이
        lab_face_ratio.data.details.common.upperAndLowerFaceLength.upperAndLowerFaceRatioLowLength
        // 입술각도
        lab_face_ratio.data.details.faceAsymmetry.lipAngle.angle.left
        lab_face_ratio.data.details.faceAsymmetry.lipAngle.angle.right
        // 포인트
        lab_face_ratio.data.partAllScore.goldenTriangleRatioDegree.a
        lab_face_ratio.data.partAllScore.goldenTriangleRatioDegree.b
        lab_face_ratio.data.partAllScore.goldenTriangleRatioDegree.c
        // 눈과 입의 평행각도차
        ?????
        // 가로비율
        lab_face_ratio.data.partAllScore.fiveEqualPartsRation
        // 턱의 면적
        lab_face_ratio.data.partAllScore.faceAsymmetryLipAngleBendAngle.left
        lab_face_ratio.data.partAllScore.faceAsymmetryLipAngleBendAngle.right

    // face shape
        // 얼굴형
        data.partAllScore.faceShape

    // eye
        // 눈각도
        data.details.angleOfEye.angleOfEyes.left
        data.details.angleOfEye.angleOfEyes.right
        // 눈높이
        data.partAllScore.heightOfAnEye.left.cm
        data.partAllScore.heightOfAnEye.right.cm
        // 눈너비
        data.partAllScore.widthOfAnEye.left.cm
        data.partAllScore.widthOfAnEye.right.cm
        // 눈간격
        data.partAllScore.eyeDistance
        // 눈동자 비율
        data.partAllScore.pupilRatio.left
        data.partAllScore.pupilRatio.right

    // double eyelid
        // 미안거간거
        data.details.lengthOfTheDistanceBetweenEyesAndEyebrow.lengthOfTheDistanceBetweenEyesAndEyebrowLeftCm
        data.details.lengthOfTheDistanceBetweenEyesAndEyebrow.lengthOfTheDistanceBetweenEyesAndEyebrowRightCm
        // 쌍꺼풀유무
        data.partAllScore.leftEyelidType
        data.partAllScore.rightEyelidType
        // 안검하수비율
        data.partAllScore.ptosisRatio.left
        data.partAllScore.ptosisRatio.right
        // 눈과 눈썹거리
        data.partAllScore.eyetoEyeBrowDistance.left.length.cm
        data.partAllScore.eyetoEyeBrowDistance.right.length.cm

    // eyebrow
        // 눈썹길이
        data.partAllScore.eyebrowLength.left.cm
        data.partAllScore.eyebrowLength.right.cm
        // 눈썹높이
        data.partAllScore.eyebrowHeight.left.cm
        data.partAllScore.eyebrowHeight.right.cm
        // 눈썹형태
        data.partAllScore.eyebrowShape
        // 눈높이와 눈썹높이비율
        ????
        // 눈썹간격
        data.partAllScore.eyebrowDistanceLength.cm

    // nose
        // 코길이
        lab_nose.data.partAllScore.lengthOfNose.cm
        // 코너비
        lab_nose.data.partAllScore.widthOfNose.cm

    // lip
        // 윗입술
        data.partAllScore.upperLib.cm
        // 아랫입술
        data.partAllScore.lowerLib.cm
        // 입꼬리각도
        data.partAllScore.lipTipShape.left
        data.partAllScore.lipTipShape.right
        // 입술비율
        ?????
        // 입술산 각도
        data.details.lipAngle
        // 입술너비
        data.details.lipWidthAndConner.lipWidth.cm
        // 인중길이
        data.details.philtrum.philtrumJawRatio.philtrums.cm
        // 턱길이
        data.details.philtrum.philtrumJawRatio.jaw.cm
        // 인중턱비율
        data.details.philtrum.philtrumJawRatio.philtrumJawRatios.ratio

    // contour
        // 광대너비
        data.partAllScore.angularRatioOfZygomaAndLowerJawZygoma.palace.cm
        // 사각턱너비
        data.partAllScore.angularRatioOfZygomaAndLowerJawZygoma.mandible.cm
        // 광대와 사각턱 비율
        data.partAllScore.angularRatioOfZygomaAndLowerJawZygoma.ratio
        // 턱코길이비율(데이터없음)
        ??
        // 앞턱길이
        data.details.frontChin.jawLength
        // 볼의 볼륨감 유무
        data.details.frontCheek.frontCheekValue
    */

    public static class Pair<T> {
        public T left;
        public T right;

        public Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }
    }
}
