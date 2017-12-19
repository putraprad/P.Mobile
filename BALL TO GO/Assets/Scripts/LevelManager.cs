using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LevelManager : MonoBehaviour {
    private static LevelManager instance;
    public static LevelManager Instance { get { return instance; } }

    public GameObject pauseMenu;
    public GameObject endMenu;
    public Transform respawnPoint;
    public Transform respawnPoint2;
    public Transform target;
    private GameObject player;
    public Text endTimerText;
    public Text scoreText;
    public Text timerText;

    private float startTime;
    private float levelDuration;
    public float silverTime = 50.0f;
    public float goldTime = 40.0f;

    private int goldScore = 150;
    private int silverScore = 75;
    private int bronzeScore = 50;

    private void Start()
    {
        instance = this;
        pauseMenu.SetActive(false);
        endMenu.SetActive(false);
        startTime = Time.time;
        player = GameObject.FindGameObjectWithTag("Player");
        player.transform.position = respawnPoint.position;
    }

    private void Update()
    {
        if (player.transform.position.y < -10.0f)
        {
            Death();
        }

        levelDuration = Time.time - startTime;

        string minutes = ((int)levelDuration / 60).ToString("00");
        string seconds = (levelDuration % 60).ToString("00.00");

        timerText.text = minutes + ":" + seconds;
    }

    public void TogglePauseMenu()
    {
        pauseMenu.SetActive(!pauseMenu.activeSelf);
        Time.timeScale = (pauseMenu.activeSelf) ? 0 : 1;
    }

    public void ToMenu()
    {
        SceneManager.LoadScene("MainMenu");
        Time.timeScale = 1;
    }

    public void Restart()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        Time.timeScale = 1;
    }

    public void NextLevel()
    {
        Sprite[] thumbnails = Resources.LoadAll<Sprite>("Levels");
        foreach(Sprite thumbnail in thumbnails)
        {
            if (thumbnail.name != SceneManager.GetActiveScene().name)
            {
                SceneManager.LoadScene(thumbnail.name);
                Time.timeScale = 1;
                break;
            }
        }
    }

    public void Victory()
    {
        foreach(Transform t in endMenu.transform.parent)
        {
            t.gameObject.SetActive(false);
        }

        endMenu.SetActive(true);

        levelDuration = Time.time - startTime;
        string minutes = ((int)levelDuration / 60).ToString("00");
        string seconds = (levelDuration % 60).ToString("00.00");
        endTimerText.text = minutes + ":" + seconds;

        if (levelDuration < goldTime)
        {
            GameManager.Instance.currency += goldScore;
            scoreText.text = "You got " + goldScore + " points";
            endTimerText.color = Color.yellow;
        }
        else if (levelDuration < silverTime)
        {
            GameManager.Instance.currency += silverScore;
            scoreText.text = "You got " + silverScore + " points";
            endTimerText.color = Color.gray;
        }
        else
        {
            GameManager.Instance.currency += bronzeScore;
            scoreText.text = "You got " + bronzeScore + " points";
            endTimerText.color = new Color(0.8f, 0.5f, 0.2f, 1.0f);
        }
        GameManager.Instance.Save();

        string saveString = "";
        LevelData level = new LevelData(SceneManager.GetActiveScene().name);
        saveString += (level.BestTime > levelDuration || level.BestTime == 0.0f) ? levelDuration.ToString() : level.BestTime.ToString();
        saveString += '&';
        saveString += silverTime.ToString();
        saveString += '&';
        saveString += goldTime.ToString();
        PlayerPrefs.SetString(SceneManager.GetActiveScene().name, saveString);

        Time.timeScale = 0;

    }

    public void Death()
    {
        if (target == null)
        {
            player.transform.position = respawnPoint2.position;
        }
        else
        {
            player.transform.position = respawnPoint.position;
        }
        Rigidbody rigid = player.GetComponent<Rigidbody>();
        rigid.velocity = Vector3.zero;
        rigid.angularVelocity = Vector3.zero;
    }
}
