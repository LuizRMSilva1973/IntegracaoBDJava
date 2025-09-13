#!/usr/bin/env bash
set -euo pipefail

# Publica a documentação de docs/wiki/ na Wiki do GitHub
# Repositório alvo: LuizRMSilva1973/IntegracaoBDJava.wiki.git
#
# Uso:
#   REMOTE=https ./scripts/publish-wiki.sh        # via HTTPS (pedirá login se necessário)
#   GH_TOKEN=<token> ./scripts/publish-wiki.sh    # via HTTPS com token (recomendado)
#   REMOTE=ssh ./scripts/publish-wiki.sh          # via SSH (precisa de chave configurada)

REPO_SLUG="LuizRMSilva1973/IntegracaoBDJava"
REMOTE_TYPE="${REMOTE:-https}"

if [[ "${REMOTE_TYPE}" == "ssh" ]]; then
  WIKI_URL="git@github.com:${REPO_SLUG}.wiki.git"
else
  if [[ -n "${GH_TOKEN:-}" ]]; then
    WIKI_URL="https://${GH_TOKEN}@github.com/${REPO_SLUG}.wiki.git"
  else
    WIKI_URL="https://github.com/${REPO_SLUG}.wiki.git"
  fi
fi

WORKDIR=".wiki-tmp"
rm -rf "${WORKDIR}"
echo "Clonando wiki: ${WIKI_URL}"
git clone "${WIKI_URL}" "${WORKDIR}"

echo "Copiando páginas da docs/wiki para a Wiki..."
cp -f docs/wiki/01-introducao-java.md       "${WORKDIR}/01-introducao-java.md"
cp -f docs/wiki/02-oop-basico.md            "${WORKDIR}/02-oop-basico.md"
cp -f docs/wiki/03-configurando-ambiente.md "${WORKDIR}/03-configurando-ambiente.md"
cp -f docs/wiki/04-jdbc-essencial.md        "${WORKDIR}/04-jdbc-essencial.md"
cp -f docs/wiki/05-sqlite-basico.md         "${WORKDIR}/05-sqlite-basico.md"
cp -f docs/wiki/06-guia-lab.md              "${WORKDIR}/06-guia-lab.md"
cp -f docs/wiki/07-erros-comuns.md          "${WORKDIR}/07-erros-comuns.md"
cp -f docs/wiki/08-glossario.md             "${WORKDIR}/08-glossario.md"

cat > "${WORKDIR}/Home.md" << 'EOF'
# Wiki — Integração com Banco de Dados (Java + JDBC)

Bem-vindo(a)! Este material foi pensado para quem ainda não cursou Orientação a Objetos (OO) e não tem experiência prévia com Java. Siga a ordem sugerida ou vá direto ao tópico necessário.

- [[01-introducao-java|01. Introdução ao Java: JDK, JRE e JVM]]
- [[02-oop-basico|02. Orientação a Objetos — o básico necessário]]
- [[03-configurando-ambiente|03. Configurando o ambiente — JDK, Maven e VS Code]]
- [[04-jdbc-essencial|04. JDBC Essencial — conceitos e boas práticas]]
- [[05-sqlite-basico|05. SQLite Básico — o banco usado no lab]]
- [[06-guia-lab|06. Guia do Laboratório — como rodar e validar]]
- [[07-erros-comuns|07. Erros Comuns e Soluções]]
- [[08-glossario|08. Glossário de Termos]]

EOF

pushd "${WORKDIR}" >/dev/null
git add .
if ! git diff --cached --quiet; then
  git commit -m "Publica conteúdo inicial da Wiki: Java básico, JDBC/SQLite e guia do laboratório"
  echo "Enviando alterações para a Wiki..."
  git push origin HEAD
else
  echo "Nenhuma alteração para publicar."
fi
popd >/dev/null

echo "Concluído. Wiki atualizada."

