package me.penguinpistol.analysisdrawing.data;

import com.google.gson.annotations.SerializedName;

public class AnalysisData {
    @SerializedName("angleOfEyebrowTail")
    private float angleOfEyebrowTail;                                   // 눈썹꼬리각도
    @SerializedName("BlackHead_Area")
    private float blackheadArea;                                        // 블랙헤드영역값
    @SerializedName("CheekRedValue")
    private float cheekRedValue;                                        // 얼굴 홍조
    @SerializedName("CrowsFeed_LeftArea")
    private float crowsFeedAreaLeft;                                    // 왼쪽 눈초리 주름 면적
    @SerializedName("CrowsFeed_RightArea")
    private float crowsFeedAreaRight;                                   // 오른쪽 눈초리 주름 면적
    @SerializedName("centralFaceBlankLengthLeft")
    private float centralFaceBlankLengthLeft;                           // 왼쪽 관궁여백 길이
    @SerializedName("centralFaceBlankLengthRight")
    private float centralFaceBlankLengthRight;                          // 오른쪽 관궁여백 길이
    @SerializedName("centralFaceBlank")
    private int centralFaceBlank;                                       // 관궁여백 상태
    @SerializedName("curvedNose72")
    private float curvedNose72;                                         // 휜코
    @SerializedName("curvedNose73")
    private float curvedNose73;                                         // 휜코
    @SerializedName("curvedNose74")
    private float curvedNose74;                                         // 휜코
    @SerializedName("curvedNose80")
    private float curvedNose80;                                         // 휜코
    @SerializedName("droopingEyesLeft")
    private int droopingEyesLeft;                                       // 왼쪽 눈 처짐 상태
    @SerializedName("droopingEyesRight")
    private int droopingEyesRight;                                      // 왼쪽 눈 처짐 상태
}

/*
"data": {
      "angleOfEyebrowTail": 31.5,
      "BlackHead_Area": 0.002146,
      "CheekRedValue": 139.440674,
      "CrowsFeed_LeftArea": 0.00254,
      "CrowsFeed_RightArea": 0.00238,
      "centralFaceBlankLengthRight": 1.9,
      "centralFaceBlankLengthLeft": 1.8,
      "centralFaceBlank": 2,
      "curvedNose72": 0.3,
      "curvedNose73": 0.8,
      "curvedNose74": 1,
      "curvedNose80": 0.8,
      "droopingEyesLeft": 1,
      "droopingEyesRight": 1,
      "darkCircles": 3,
      "EyeWrinkle_LeftArea": 0.000219,
      "EyeWrinkle_RightArea": 0.001955,
      "NasolabialFolds_LeftArea": 0.001419,
      "NasolabialFolds_RightArea": 0.003835,
      "PoresBetweenBrow_Have": 0,
      "PoresCheeks_Left": 0,
      "PoresCheeks_Right": 0,
      "PoresForehead_Have": 0,
      "SkinAcne": 7,
      "SkinAge": 26,
      "skinScore": 94,
      "SkinBlackHeads": 2,
      "SkinBlackHeadsRectangle": [
        {
          "height": 10.746903,
          "left": 373.70285,
          "top": 445.24286,
          "width": 11.494552
        },
        {
          "height": 8.905165,
          "left": 271.84198,
          "top": 180.47168,
          "width": 8.159542
        }
      ],
      "SkinColorHueDelta": 2,
      "SkinColorLevel": 1,
      "SkinCrowFeetLeftPathPoints": [
        [
          148,
          240,
          150,
          198.00002,
          195.99998,
          370
        ]
      ],
      "SkinCrowFeetRightPathPoints": [
        [
          346,
          245,
          347,
          438,
          391.99997
        ]
      ],
      "SkinCrowsFeed_Left": 1,
      "SkinCrowsFeed_Right": 1,
      "SkinEyeFineLineScore_Left": 0.014601,
      "SkinEyeFineLineScore_Right": 0.024937,
      "SkinEyeFineLine_Left": 0,
      "SkinEyeFineLine_Right": 0,
      "SkinEyeWrinkleLeftPathPoints": [
        [
          241,
          324,
          265,
          235.00002
        ],
        [
          351,
          155,
          163,
          254.00002
        ]
      ],
      "SkinEyeWrinkleRightPathPoints": [
        [
          301,
          145,
          257
        ],
        [
          301,
          155,
          163,
          424
        ]
      ],
      "SkinForeHeadWrinkle": 0,
      "SkinForeHeadWrinkleArea": 0.01848,
      "SkinForeheadWrinklePathPoints": [
        [
          306,
          159,
          304,
          217.00002
        ],
        [
          301,
          155,
          163,
          254.00002
        ]
      ],
      "SkinHighlight": 5,
      "SkinHighlightPrec": 0.004235,
      "SkinHighlightRectangle": [
        {
          "height": 10,
          "left": 256,
          "top": 296,
          "width": 6
        },
        {
          "height": 6,
          "left": 320,
          "top": 275,
          "width": 11
        }
      ],
      "skinAgeState": 1,
      "SkinLevel": 72,
      "SkinMole": 2,
      "SkinMoleRectangle": [
        {
          "height": 7.281287,
          "left": 463.4175,
          "top": 697.7914,
          "width": 8.086893
        },
        {
          "height": 3.99169,
          "left": 483.698,
          "top": 640.1306,
          "width": 4.73697
        }
      ],
      "SkinNasolabialFoldLeftPathPoints": [
        [
          370,
          145,
          257,
          371,
          427
        ],
        [
          410,
          414,
          361
        ]
      ],
      "SkinNasolabialFoldRightPathPoints": [
        [
          411,
          358.00003,
          257
        ],
        [
          360,
          408,
          359,
          420
        ]
      ],
      "SkinNasolabialFolds_Left": 1,
      "SkinNasolabialFolds_Right": 0,
      "SkinPandaEye_Left": 1,
      "SkinPandaEye_Left_Artery": 1,
      "SkinPandaEye_Left_Artery_Have": 1,
      "SkinPandaEye_Left_Pigment": 1,
      "SkinPandaEye_Left_Pigment_Have": 2,
      "SkinPandaEye_Left_Shadow": 1,
      "SkinPandaEye_Left_Shadow_Have": 0,
      "SkinPandaEye_Right": 1,
      "SkinPandaEye_Right_Artery": 0,
      "SkinPandaEye_Right_Artery_Have": 0,
      "SkinPandaEye_Right_Pigment": 0,
      "SkinPandaEye_Right_Pigment_Have": 0,
      "SkinPandaEye_Right_Shadow": 0,
      "SkinPandaEye_Right_Shadow_Have": 0,
      "SkinPimple": 2,
      "SkinPimpleRectangle": [
        {
          "height": 11.381465,
          "left": 205.085,
          "top": 401.2506,
          "width": 11.686886
        },
        {
          "height": 8.587759,
          "left": 474.17477,
          "top": 258.94064,
          "width": 8.050905
        }
      ],
      "SkinRosaceaChin": 1,
      "SkinRosaceaForehead": 0,
      "SkinRosaceaLeftcheek": 1,
      "SkinRosaceaNose": 1,
      "SkinRosaceaRightcheek": 1,
      "SkinSpot": 2,
      "SkinSpotRectangle": [
        {
          "height": 4.4625,
          "left": 437.86996,
          "top": 323.45,
          "width": 4.23
        },
        {
          "height": 4.4625,
          "left": 305.6825,
          "top": 427.57498,
          "width": 4.75875
        }
      ],
      "SkinSpotState": "sp01",
      "SkinType": 2,
      "TregionShinyRatio": 0.008779,
      "age": 26,
      "angleOfEyebrow": 13.8,
      "angleOfEyesInnerCornerLeft": 57,
      "angleOfEyesInnerCornerRight": 64.6,
      "angleOfEyesLeft": 9.3,
      "angleOfEyesOuterCornerLeft": 62.7,
      "angleOfEyesOuterCornerRight": 72.6,
      "angleOfEyesRight": 11,
      "angleOfEyesState": 2,
      "angularRatioOfZygomaAndLowerJawLowerJawLength": 9.8,
      "angularRatioOfZygomaAndLowerJawLowerJawRatio": 1,
      "angularRatioOfZygomaAndLowerJawZygomaLength": 12.3,
      "angularRatioOfZygomaAndLowerJawZygomaRatio": 1.3,
      "beauty_score": 82,
      "blackHeads": "sp01",
      "cheek_shape": "ea02",
      "chinNoseLengthRatioChin": 2.6,
      "chinNoseLengthRatioChinRatio": 0.4,
      "chinNoseLengthRatioNose": 6.7,
      "chinNoseLengthRatioNoseRatio": 1,
      "chinLength": 2.6,
      "noseWidth": 2.9,
      "tearGlandsChin": 10.4,
      "conditionOuterEyebrows": 2,
      "emotion": 2,
      "eyeDistance": 3.5,
      "eyeTailAngle": 2,
      "eye_distance": "ad03",
      "eye_shape": "ba01",
      "eyebagYesValue": 2,
      "eyebags_no": 0.915263,
      "eyebags_yes": 0.084737,
      "eyebrowDistance": 2.8,
      "eyebrowHeighLeft": 1.4,
      "eyebrowHeighRight": 1.4,
      "eyebrowLengthLeft": 3.9,
      "eyebrowLengthRight": 3.8,
      "eyebrowToNoseTip": 6.7,
      "eyebrow_concentration": "ab03",
      "eyebrow_concentration_distribution": "ac01",
      "eyebrow_distance": "ae02",
      "eyebrow_shape": "aa04",
      "eyetoEyeBrowDistanceLeft": 1.1,
      "eyetoEyeBrowDistanceLeftText": 2,
      "eyetoEyeBrowDistanceRight": 1.1,
      "eyetoEyeBrowDistanceRightText": 2,
      "eyeLevelAndEyebrowHeightRatioRightBrowHeight": 1.5,
      "eyeLevelAndEyebrowHeightRatioRightBrowRatio": 1.2,
      "eyeLevelAndEyebrowHeightRatioLeftBrowHeight": 1.3,
      "eyeLevelAndEyebrowHeightRatioLeftBrowRatio": 1.1,
      "eyeLevelAndEyebrowHeightRatioRightEyeLevelLength": 1.2,
      "eyeLevelAndEyebrowHeightRatioRightEyeLevelRatio": 1,
      "eyeLevelAndEyebrowHeightRatioLeftEyeLevelLength": 1.1,
      "eyeLevelAndEyebrowHeightRatioLeftEyeLevelRatio": 1,
      "faceArea": 165436,
      "faceAllScore": 78,
      "faceAsymmetry2EyeAngleLeft": 90.1,
      "faceAsymmetry2EyeAngleRight": 89.9,
      "faceAsymmetry2EyeBendAngleLeft": 0.1,
      "faceAsymmetry2EyeBendAngleRight": 0.1,
      "faceAsymmetry2LipAngleLeft": 90.8,
      "faceAsymmetry2LipAngleRight": 89.2,
      "faceAsymmetry2LipBendAngleLeft": 0.8,
      "faceAsymmetry2LipBendAngleRight": 0.8,
      "faceAsymmetry2faceAreaLeft": 453.2,
      "faceAsymmetry2faceAreaRight": 443.9,
      "faceAsymmetry2faceAreaState": 0,
      "faceAsymmetry2parallelAngleState": 0,
      "faceAsymmetryAreaLeft": 453.2,
      "faceAsymmetryAreaRight": 443.9,
      "faceAsymmetryCardinalAngleNe": 92.5,
      "faceAsymmetryCardinalAngleNw": 87.5,
      "faceAsymmetryCardinalAngleSe": 88.9,
      "faceAsymmetryCardinalAngleSw": 91.1,
      "faceAsymmetryCardinalAngleTotal": 360,
      "faceAsymmetryLeftChinAsymmetricAngle": 3.3,
      "faceAsymmetryLeftChinCenterLength": 5.4,
      "faceAsymmetryLeftEyeAsymmetricAngle": 1.1,
      "faceAsymmetryLeftEyeCenterLength": 4.3,
      "faceAsymmetryLeftLipAsymmetricAngle": 0.4,
      "faceAsymmetryLeftLipCenterLength": 1.8,
      "faceAsymmetryReflexAnglePoint100": 0.7,
      "faceAsymmetryReflexAnglePoint71": 3,
      "faceAsymmetryReflexAnglePoint72": 0.1,
      "faceAsymmetryReflexAnglePoint73": 0.3,
      "faceAsymmetryReflexAnglePoint74": 3.4,
      "faceAsymmetryReflexAnglePoint80": 1.4,
      "faceAsymmetryRightChinAsymmetricAngle": 3.3,
      "faceAsymmetryRightChinCenterLength": 5.5,
      "faceAsymmetryRightLipAsymmetricAngle": 0.4,
      "faceAsymmetryRightLipCenterLength": 2,
      "faceAsymmetryRigthEyeAsymmetricAngle": 1.1,
      "faceAsymmetryRigthEyeCenterLength": 4.4,
      "faceLength": 17.7,
      "face_shape": "ga04",
      "facetype_circular": "0.000041",
      "facetype_elliptic": "0.894408",
      "facetype_long": "0.000709",
      "facetype_prism": "0.000057",
      "facetype_square": "0.000043",
      "facetype_triangle": "0.104741",
      "facialWidth": 12.4,
      "fiveEqualPartsRatioMeasureRatioFirst": 0.6,
      "fiveEqualPartsRatioMeasureRatioSecond": 1,
      "fiveEqualPartsRatioMeasureRatioThird": 1.1,
      "fiveEqualPartsRatioMeasureRatioFourth": 1,
      "fiveEqualPartsRatioMeasureRatioFifth": 0.8,
      "fiveEqualPartsRatioNormalRatioFirst": 0.65,
      "fiveEqualPartsRatioNormalRatioSecond": 1,
      "fiveEqualPartsRatioNormalRatioThird": 1,
      "fiveEqualPartsRatioNormalRatioFourth": 1,
      "fiveEqualPartsRatioNormalRatioFifth": 0.65,
      "foreheadLength": 11.2,
      "foreheadToEyebrowTip": 5.7,
      "gender": 0,
      "glasses_frame": 0,
      "glasses_shape": 0,
      "glasses_size": 0,
      "glasses_thick": 0,
      "glasses_type": 0,
      "goldenTriangleRatioDegreeAngleA": 77.13404896566637,
      "goldenTriangleRatioDegreeAngleB": 51.459163068975,
      "goldenTriangleRatioDegreeAngleC": 51.406787965358646,
      "heightOfAnEyeLeft": 1.1,
      "heightOfAnEyeRight": 1.2,
      "jawLengthCondition": 0,
      "jaw_shape": "fa01",
      "lacrimalglandtojawlength": 9.9,
      "landmark118": [
        [
          152.69994,
          396.94275
        ],
        [
          152.32861,
          423.4388
        ]
      ],
      "landmark171": [
        [
          206.57721,
          404.6406
        ],
        [
          228.32564,
          389.8061
        ]
      ],
      "leftArea": 453.2,
      "left_eyelid": 0,
      "lengthOfJaw": 2.5,
      "lengthOfNose": 6.7,
      "lengthOfPhiltrum": 0.9,
      "lipAngle": 159.7,
      "lipRatioLowRatio": 1,
      "lipRatioTopRatio": 1,
      "lipTipShapeLeft": 18.5,
      "lipTipShapeRight": 19.7,
      "lipTipShapeText": 2,
      "lip_peak": "db02",
      "lipsRatioIdealLipRatioFirst": 1,
      "lipsRatioIdealLipRatioSecond": 1.6,
      "lipsRatioLowerLip": 1.1,
      "lipsRatioLowerLipRatio": 1.4,
      "lipsRatioState": 0,
      "lipsRatioUpperLip": 0.8,
      "lipsRatioUpperLipRatio": 1,
      "lowerFacialRatioPhilanthropy": 28.2,
      "lowerFacialRatioPhilanthropyRatio": 1,
      "lowerFacialRatiochin": 89.5,
      "lowerFacialRatiochinRatio": 3.2,
      "lowerFacialRatiolips": 55.6,
      "lowerFacialRatiolipsRatio": 2,
      "lowerAndLipRatioLipAndChinFirst": 1,
      "lowerAndLipRatioLipAndChinSecond": 1.1,
      "lowerAndLipRatioLowRatioFirst": 1,
      "lowerAndLipRatioLowRatioSecond": 1.2,
      "lowerAndLipRatioLowRatioThird": 1.2,
      "lowerLib": 32.7,
      "lengthOfTheDistanceBetweenEyesAndEyebrowRight": 1.4,
      "lengthOfTheDistanceBetweenEyesAndEyebrowLeft": 1.4,
      "mandibularAngleState": 2,
      "mandibularAngleWidth": 10.4,
      "mandibularLength": 16.7,
      "mouth_thickness": "da01",
      "mustache_length": 0,
      "mustache_shape": 0,
      "mustache_thick": 0,
      "mustache_type": 0,
      "noseTipToChin": 5.2,
      "nose_shape": "ca02",
      "noselengthState": 2,
      "philtrumJawRatioJaw": 2.5,
      "philtrumJawRatioJawRatio": 2.9,
      "philtrumJawRatioPhiltrum": 28.2,
      "philtrumJawRatioPhiltrumRatio": 1,
      "philtrumJawRatioState": 2,
      "philtrumState": 1,
      "photoHeight": 892,
      "photoWidth": 720,
      "pitch_angle": -4.005771636962891,
      "ptosisState": 3,
      "pantone_standard_colorCode": 10,
      "pantone_standard_rgb": "bd9584",
      "pantone_standard_tone": "cool",
      "ptosisStatusState": 2,
      "ptosisRightRatiotop": 0.7,
      "ptosisRightRatiolow": 1,
      "ptosisLeftRatiotop": 0.8,
      "ptosisLeftRatiolow": 1,
      "race": 1,
      "rightArea": 443.9,
      "right_eyelid": 0,
      "risorius_no": "0.559970",
      "risorius_yes": "0.440030",
      "risoriusState": 3,
      "roll_angle": 0.06596451997756958,
      "skinAcneState": "sp01",
      "skinMoleState": "sp01",
      "skinPimpleState": "sp01",
      "statusOfEyeBrowTailLooked": 1,
      "statusOfEyeBrowTailShape": 1,
      "statusOfEyeTailLeft": 2,
      "statusOfEyeTailRight": 0,
      "statusOfTheDistanceBetweenEyesAndEyebrowRight": 1,
      "statusOfTheDistanceBetweenEyesAndEyebrowLeft": 1,
      "temple_full": "0.999877",
      "temple_seg": "0.000123",
      "templesState": 4,
      "translate_x": -2.6580636501312256,
      "translate_y": 26.840518951416016,
      "translate_z": 309.4039001464844,
      "upperAndLowerFaceLowLength": 5.2,
      "upperAndLowerFaceLowRatio": 1,
      "upperAndLowerFaceMiddleLength": 6.7,
      "upperAndLowerFaceMiddleRatio": 1.1,
      "upperAndLowerFaceRatioBabyFace": 0,
      "upperAndLowerFaceRatioLowLength": 5.2,
      "upperAndLowerFaceRatioLowRatio": 0.9,
      "upperAndLowerFaceRatioMiddleLength": 6.7,
      "upperAndLowerFaceRatioMiddleRatio": 1,
      "upperAndLowerFaceRatioTopLength": 5.7,
      "upperAndLowerFaceRatioTopRatio": 1,
      "upperAndLowerFaceText": "1이면 하정보다 중정이 길다. 0이면 중정보다 하정이 길다.",
      "upperLib": 22.8,
      "vaAngle": 24.648392612409346,
      "viAngle": 72.88311847198675,
      "widthOfAnEyeLeft": 2.9,
      "widthOfAnEyeRight": 2.9,
      "widthOfCentralFace": 4.1,
      "widthOfForehead": 15.7,
      "widthOfJaw": 9.8,
      "widthOfMandibularAngle": 10.1,
      "widthOfMandidularBone": 9.4,
      "widthOfMouth": 4.5,
      "widthOfNose": 3.4,
      "widthOfNoseState": 0,
      "widthOfZygoma": 10.4,
      "yaw_angle": 1.213253378868103
    }
 */
