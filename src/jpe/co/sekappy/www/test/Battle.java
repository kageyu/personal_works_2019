package jpe.co.sekappy.www.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;





public class Battle {

	static ArrayList<String> log = new ArrayList<>();

	private static String randomNomber() {

		String S;

		try {

			SecureRandom random = SecureRandom.getInstanceStrong();

			random.nextInt(10);

			int i = 1 + random.nextInt(10 - 1);

			S = String.valueOf(i);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

			S = "0";

		}

		return S;

	}

	/**
	 * キャラクターの名前の長さを決める関数
	 * 最大値、最小値は手入力
	 * @return ランダムに出力されたキャラクターの名前の長さ
	 */
	private static int addRandomNameLength() {

		int nameLength = 0;

		final int nameLengthMax = 6;
		final int nameLengthMin = 2;

		SecureRandom random = new SecureRandom();
		nameLength = nameLengthMin + random.nextInt(nameLengthMax - nameLengthMin);

		return nameLength;
	}

	/**
	 * キャラクターの名前に使う文字をランダム生成する関数
	 * この関数を繰り返し使用してランダムな文字列を生成する
	 * 候補の文字は手入力
	 * @return ランダムに生成した1文字
	 */
	private static String addRandomChar() {

		final String nameSource = "アイウエオカキクケコサシスセソタチツテトナニヌネノマミムメモ"
				+ "ハヒフヘホラリルレロヤユヨンガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ";

		SecureRandom random = new SecureRandom();
		int num = random.nextInt(nameSource.length());

		String randomChar = String.valueOf(nameSource.charAt(num - 1));

		return randomChar;
	}

	/**
	 * キャラクターの名前を生成する関数
	 * @return 生成した名前の文字列
	 */
	public static String addRandomName() {

		String name = "";
		int nameLength = addRandomNameLength();

		for (int i = 0; i < nameLength; i++) {

			String C = addRandomChar();
			final String S = "ン";

			if (i == 0 && S.equals(C)) {
				i -= 1;
			} else {
				name += C;
			}
		}
		return name;
	}

	/**
	 * ヒーローのパラメーターを設定する関数
	 * @param H Heroクラスのインスタンスを引数として設定
	 */
	public static void setHeroParametor(Hero H) {

		H.setName(addRandomName());
		H.setAgi(randomNomber());
		H.setAtk(randomNomber());
		H.setHp(randomNomber());
		H.setNowhp(H.getHp());

	}

	/**
	 * ヒーロー2人のagiを比較する関数
	 * @param H1
	 * @param H2
	 * @return Hero1の方が早ければプラスの値、Hero2の方が早ければマイナスの値、同じなら0
	 */
	private static int checkAgi(Hero H1, Hero H2) {

		int agiFlag = 0;

		int H1Agi = Integer.parseInt(H1.getAgi());
		int H2Agi = Integer.parseInt(H2.getAgi());

		agiFlag = H1Agi - H2Agi;

		return agiFlag;

	}

	/**
	 * ログを出力する関数
	 * @param log 出力する内容を引数として受け取る
	 */
	private static void addLog() {

		try {
			final String logPath = "c:\\data\\log";
			Path path = Paths.get(logPath);

			String logDir = "c:\\data\\log";
			String logFileName = "log.txt";

			String strDate = nowDateFileName();
			logFileName = strDate + ".txt";

			//ディレクトリが存在しない場合は作成
			if (!Files.exists(path)) {
				Files.createDirectories(Paths.get(logDir));
			}

			//ファイルが存在しない場合は作成
			if (!Files.exists(Paths.get(logDir, logFileName))) {
				Files.createFile(Paths.get(logDir, logFileName));
			}

			//ファイルに書き込み
			Files.write(Paths.get(logDir, logFileName), log);

			//ファイルの読み込み
			List<String> test2 = Files.readAllLines(Paths.get(logDir, logFileName));

			//読み込んだ文字をコンソールに出力
			for (String s : test2) {
				System.out.println(String.format("Files:%s", s));
			}

//			String brLine;
//			try (BufferedReader br = Files.newBufferedReader(Paths.get(logDir, logFileName))) {
//				while ((brLine = br.readLine()) != null) {
//					System.out.println(String.format("brLine:%s", brLine));
//				}
//			}
//
//			try(Stream<String> stream =	Files.lines(Paths.get(logDir, logFileName))){
//				stream.forEach(streamLine -> {
//					System.out.println(String.format("Stream:%s",streamLine));
//				});
//			}


			//ログファイル一覧を更新する処理
			ArrayList<String>  logList= new ArrayList<>();

			String logListDir = "c:\\data";
			String logListFileName = "List.txt";

			//ファイルが存在しない場合は作成
			if (!Files.exists(Paths.get(logListDir, logListFileName))) {
				Files.createFile(Paths.get(logListDir, logListFileName));
			}

			//ログフォルダ内のファイル名の一覧を取得
			try(Stream<Path> logListLine =	Files.list(path)){
				logListLine.forEach(streamLine -> {
//					System.out.println(String.format("Stream:%s",streamLine));
					String S = streamLine.toString();

					logList.add(S);
				});
			}

//			logList.add("Hello World!");
			//ファイルに書き込み
			Files.write(Paths.get(logListDir, logListFileName), logList);




		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 現在時刻を取得する
	 * @return 現在時刻を文字列の方に直したもの
	 */
	static String nowDateFileName() {

		Date date = new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy_MM_dd_kk_mm_ss_SSS");
		String strDate = dateFormat.format(date);

		return strDate;
	}

	/**
	 * 現在時刻を取得する
	 * @return 現在時刻を文字列の方に直したもの
	 */
	static String nowDateMessage() {

		Date date = new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd kk:mm:ss.SSS");
		String strDate = dateFormat.format(date);

		return strDate;
	}

	/**
	 * ダメージメッセージのログを出力する
	 * @param atackHero 攻撃するHero
	 * @param takeHero  攻撃されるHero
	 */
	private static void addDamageLog(Hero atackHero, Hero takeHero) {

		String[] damageMessage = new String[3];
		damageMessage[0] = atackHero.getName() + "の攻撃！";
		damageMessage[1] = takeHero.getName() + "に" + atackHero.getAtk() + "のダメージ！";
		damageMessage[2] = takeHero.getName() + " 残り" + takeHero.getNowhp() + "/" + takeHero.getHp();

		for (String s:damageMessage) {
			String message = nowDateMessage() + " " + s;
			System.out.println(message);
			log.add(message);
		}

	}

	/**
	 * agiが高いHeroがダメージを与える
	 * agiが同じの場合は双方がダメージを与える
	 * @param H1
	 * @param H2
	 */
	private static void damageStep1(Hero H1, Hero H2) {

		int agiFlag = checkAgi(H1, H2);

		int H1HP = Integer.parseInt(H1.getNowhp());
		int H1ATK = Integer.parseInt(H1.getAtk());
		int H2HP = Integer.parseInt(H2.getNowhp());
		int H2ATK = Integer.parseInt(H2.getAtk());

		if (agiFlag > 0) {

			int nextH2HP = H2HP - H1ATK;

			H2.setNowhp(String.valueOf(nextH2HP));

			addDamageLog(H1,H2);

		} else if (agiFlag < 0) {

			int nextH1HP = H1HP - H2ATK;

			H1.setNowhp(String.valueOf(nextH1HP));

			addDamageLog(H2,H1);

		} else {

			int nextH1HP = H1HP - H2ATK;
			int nextH2HP = H2HP - H1ATK;

			H1.setNowhp(String.valueOf(nextH1HP));
			H2.setNowhp(String.valueOf(nextH2HP));

			addDamageLog(H1,H2);
			addDamageLog(H2,H1);

		}

	}

	/**
	 *Heroのnowhpが0以下になっているかチェックする関数
	 * @param H1
	 * @param H2
	 * @return どちらかのHeroのnowhpが0以下の場合にfalesを返す
	 */
	private static boolean checkHP(Hero H1, Hero H2) {

		int H1HP = Integer.parseInt(H1.getNowhp());
		int H2HP = Integer.parseInt(H2.getNowhp());

		boolean B = (H1HP > 0) && (H2HP > 0);

		return B;

	}

	/**
	 *agiの低いHeroがダメージを与える
	 *agiが同じ場合は発生しない
	 * @param H1
	 * @param H2
	 */
	private static void damageStep2(Hero H1, Hero H2) {

		int agiFlag = checkAgi(H1, H2);

		int H1HP = Integer.parseInt(H1.getNowhp());
		int H1ATK = Integer.parseInt(H1.getAtk());
		int H2HP = Integer.parseInt(H2.getNowhp());
		int H2ATK = Integer.parseInt(H2.getAtk());

		if (agiFlag > 0) {

			int nextH1HP = H1HP - H2ATK;

			H1.setNowhp(String.valueOf(nextH1HP));

			addDamageLog(H2,H1);

		} else if (agiFlag < 0) {

			int nextH2HP = H2HP - H1ATK;

			H2.setNowhp(String.valueOf(nextH2HP));

			addDamageLog(H1,H2);

		}

	}

	public static void main(String[] args) {

		Hero Hero1 = new Hero();
		Hero Hero2 = new Hero();

		setHeroParametor(Hero1);
		setHeroParametor(Hero2);

		log.add(nowDateMessage() + " " + Hero1.toString());
		log.add(nowDateMessage() + " " + Hero2.toString());

		checkAgi(Hero1, Hero2);

		while (checkHP(Hero1, Hero2)) {

			damageStep1(Hero1, Hero2);

			if (checkHP(Hero1, Hero2)) {

				damageStep2(Hero1, Hero2);

			}

		}

		//勝敗表示文
		String resultMessage = "";

		if(Integer.parseInt(Hero1.getNowhp())>0) {
			resultMessage = Hero1.getName() + "の勝ち！";
		}else if(Integer.parseInt(Hero2.getNowhp())>0) {
			resultMessage = Hero2.getName() + "の勝ち！";
		}else {
			resultMessage = "引き分け！";
		}

		String resultLog = nowDateMessage() + " " + resultMessage;
		System.out.println(resultLog);
		log.add(resultLog);

		System.out.println(Hero1.toString() + Hero2.toString());

		addLog();






	}

}
