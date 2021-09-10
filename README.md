# Fetch
Simple Java Program that receives a list of UUIDs from Mojang's API using usernames and the given time that username was held, since Mojang allowed usernames to be changed after 2014.

I made this because I aquired OriginalPvP (og-mc.net) that was a popular Minecraft server in 2012-2014. However, I only had a list of usernames from the date ownership was transferred eight years ago with their affiliated rank group. I wanted to return the purchased ranks to the people that purchased them before in the past. All plugins now identify accounts via UUIDs rather than usernames since 2014, so I created this program that inputed usernames, seperated with commas and no whitespaces, and return a list of UUIDs. Using this I was to able to assign ranks to the UUIDs from the username list from 2014, which contained around 1000+ accounts with eight rank groups.