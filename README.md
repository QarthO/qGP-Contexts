<div align="center">

[![Modrinth][modrinth-shield]][modrinth-url]
[![Discord][discord-shield]][discord-url]
[![GitHub][github-shield]][github-url]
[![Paypal][paypal-shield]][paypal-url]
[![Supported Minecraft Versions][versions-shield]][versions-url]
</div>

---

# qGP-Contexts Plugin

---

### Adds GriefPrevention contexts to LuckPerms

Now with [HuskClaims][huskclaims-url] support!
---

Requirements:

- Paper/Forks 1.21+ (Spigot not supported)
- Java 21+
- [LuckPerms][luckperms-url]
- [GriefPrevention][griefprevention-url] or [HuskClaims][huskclaims-url]

---

## Contexts

| Key                                 | Description                              | Example Value |
|-------------------------------------|------------------------------------------|---------------|
| ``griefprevention:claim-id``        | id of the claim the player is in         | ``32``        |
| ``griefprevention:in-claim``        | if the player is in a claim              | ``true``      |
| ``griefprevention:in-admin-claim``  | if the claim is an admin claim           | ``false``     |
| ``griefprevention:is-owner``        | if the player is the owner of the claim  | ``false``     |
| ``griefprevention:trust-access``    | if the player has access trust level*    | ``true``      |
| ``griefprevention:trust-build``     | if the player has build trust level*     | ``true``      |
| ``griefprevention:trust-edit``      | if the player has edit trust level*      | ``false``     |
| ``griefprevention:trust-inventory`` | if the player has inventory trust level* | ``true``      |
| ``griefprevention:trust-manage``    | if the player has manage trust level*    | ``false``     |

**Note: Claim owners default to ``true`` for all trust level contexts if they're in their own claim*

For HuskClaims:

- Replace ``griefprevention:`` with ``huskclaims:`` in the context key
- HuskClaims doesn't have a ``claim-id`` key
- Supports custom made trust levels too!

If you need any help or support join the quartzdev.gg [discord][discord-url]!

---

## Example Setup:

Want to prevent players from setting homes in claims they don't have atleast build trust in? You can do the following

Set ``essentials.sethome`` to ``false`` with the following contexts:

- ``griefprevention:in-claim`` = ``true``
- ``griefprevention:trust-build`` = ``false``

![GP Contexts Permissions][perms-example-img]

---

This plugin uses [bStats][bstats-url]. You can opt-out in the bStats config
<div align="center">

[![bStats for qTemplate Plugin][bstats-plugin-svg]][bstats-plugin-url]
</div>

[luckperms-url]: https://luckperms.net/

[griefprevention-url]: https://modrinth.com/plugin/griefprevention

[perms-example-img]: https://github.com/QarthO/qGP-Contexts/blob/main/img/gpcontexts%20perms.png?raw=true

[huskclaims-url]: https://www.spigotmc.org/resources/huskclaims-1-17-1-21-modern-golden-shovel-land-claiming-fully-cross-server-compatible.114467/

[modrinth-shield]: https://img.shields.io/badge/Download-00AF5C?logo=modrinth&logoColor=white&style=for-the-badge

[modrinth-url]: https://modrinth.com/plugin/qGP-Contexts

[discord-shield]: https://img.shields.io/badge/Discord-5865F2?logo=discord&logoColor=white&style=for-the-badge

[discord-url]: https://quartzdev.gg/discord/

[github-shield]: https://img.shields.io/badge/Source-181717?logo=github&logoColor=white&style=for-the-badge

[github-url]: https://github.com/qartho/qGP-Contexts

[paypal-shield]: https://img.shields.io/badge/Donate-00457C?logo=paypal&logoColor=white&style=for-the-badge

[paypal-url]: https://quartzdev.gg/paypal/

[versions-shield]: https://img.shields.io/badge/1.20+-blue?style=for-the-badge&label=Minecraft%20Versions

[versions-url]: https://modrinth.com/plugins/qGP-Contexts

[bstats-url]: https://bstats.org/

[bstats-plugin-svg]: https://bstats.org/signatures/bukkit/qGP-Contexts.svg

[bstats-plugin-url]: https://bstats.org/plugin/bukkit/qGP-Contexts/
