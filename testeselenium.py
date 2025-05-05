from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import shutil

# Localiza o caminho do Chromium
chromium_path = shutil.which("chromium-browser")

options = Options()
options.binary_location = chromium_path
options.add_argument('--headless')
options.add_argument('--no-sandbox')
options.add_argument('--disable-dev-shm-usage')

service = Service("/usr/bin/chromedriver")  # Caminho fixo

driver = webdriver.Chrome(service=service, options=options)

driver.get("https://www.google.com")
print("Título da página:", driver.title)

try:
    consent_button = driver.find_element(By.ID, "L2AGLb")
    consent_button.click()
    print("Botão de cookies clicado.")
except:
    print("Nenhum botão de cookies encontrado.")

driver.quit()
print("Teste concluído com sucesso.")

